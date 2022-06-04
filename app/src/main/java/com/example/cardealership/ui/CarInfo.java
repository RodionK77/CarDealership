package com.example.cardealership.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cardealership.R;
import com.example.cardealership.ui.account.User;
import com.example.cardealership.ui.search.CarCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class CarInfo extends AppCompatActivity implements Serializable {

    String str1 = "Марка\nСтрана марки\nМодель\nЦвет\nКласс автомобиля\nКол-во дверей\nКол-во мест\nРасположение руля";
    String str2 = "Тип двигателя\nОбъём\nМощность\nКоробка передач";
    String str3 = "Ускорение\nПотребление";
    String str4 = "Длина\nШирина\nВысота\nОбъём багажника\nОбъём топливного бака";
    ImageView image, bookmark;
    TextView name;
    TextView price;
    TextView one;
    TextView two;
    TextView three;
    TextView four;
    CarCard item;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    FirebaseUser currentUser;
    User user;
    String bookstores;
    Boolean book_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);
        book_set = false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        item = (CarCard) getIntent().getSerializableExtra("EXTRA_MESSAGE");
        image = findViewById(R.id.iv_rv_card_info);
        name = findViewById(R.id.info_card_name_info);
        price = findViewById(R.id.rv_card_price_info);
        one = findViewById(R.id.info_fields_main_data);
        two = findViewById(R.id.info_fields_engine_data);
        three = findViewById(R.id.info_fields_exploitation_data);
        four = findViewById(R.id.info_fields_size_data);
        bookmark = findViewById(R.id.iv_bookmark_info);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
            mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Ошибка доступа", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        user = (User)task.getResult().getValue(User.class);
                        bookstores = user.getBookstores();
                        if (bookstores.contains(Integer.toString(item.getIndex()))){
                            bookmark.setColorFilter(getResources().getColor(R.color.mark_true));
                            //bookmark.setBackgroundResource(R.drawable.ic_bookmark_true);
                            book_set = true;
                        }
                    }
                }
            });
        }

        Picasso.get().load(item.getImage()).into(image);
        price.setText(String.format("%,.0f", Float.valueOf(item.getPrice())) + " ₽");
        name.setText(item.getBrand() + " " + item.getName());
        one.setText(item.getBrand() + "\n" + item.getCountry() + "\n" +item.getName() + "\n" + item.getColor() + "\n" + item.getCar_class() + "\n" + String.valueOf(item.getDoors()) + "\n" + String.valueOf(item.getPlaces()) + "\n" + item.getWheel()  );
        two.setText(item.getEngine() + "\n" + String.valueOf(item.getCapacity()) + " л.\n" + String.valueOf(item.getPower()) + " л. с.\n" + item.getTransmission());
        three.setText(String.valueOf(item.getAcceleration()) + " с.\n" + String.valueOf(item.getConsumption()) + " л.");
        four.setText(String.valueOf(item.getLength()) + " мм.\n" + String.valueOf(item.getWidth()) + " мм.\n" + String.valueOf(item.getHeight()) + " мм.\n" + String.valueOf(item.getTrunk()) + " л.\n" + String.valueOf(item.getFuel_tank()) + " л.");
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser!=null){
                    if(book_set == true){
                        user.delBookstores(item.getIndex());
                        //bookmark.setBackgroundResource(R.drawable.ic_bookmark_false);
                        bookmark.setColorFilter(getResources().getColor(R.color.mark_false));
                        Toast.makeText(getApplicationContext(),"Закладка удалена", Toast.LENGTH_SHORT).show();
                        mDataBase.setValue(user);
                    }else if(book_set == false){
                        user.addBookstores(item.getIndex());
                        bookmark.setColorFilter(getResources().getColor(R.color.mark_true));
                        //bookmark.setBackgroundResource(R.drawable.ic_bookmark_true);
                        book_set = true;
                        mDataBase.setValue(user);
                        Toast.makeText(getApplicationContext(),"Закладка добавлена", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Для добавления закладок - зарегестрируйтесь", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

}
