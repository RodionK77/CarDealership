package com.example.cardealership.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.cardealership.ui.CarInfo;
import com.example.cardealership.DB.DBmanager;
import com.example.cardealership.R;
import com.example.cardealership.ui.search.CarCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    Context context;
    CardView cv0;
    CardView cv1;
    CardView cv2;
    CardView cv3;
    CardView cv4;
    TextView brand, price;
    ImageView img;
    int idx = 0;
    private DatabaseReference mDataBase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_home, container, false);

        cv0 = RootView.findViewById(R.id.card_view0);
        cv1 = RootView.findViewById(R.id.card_view1);
        cv2 = RootView.findViewById(R.id.card_view2);
        cv3 = RootView.findViewById(R.id.card_view3);
        cv4 = RootView.findViewById(R.id.card_view4);
        brand = RootView.findViewById(R.id.brand_card0);
        price = RootView.findViewById(R.id.price_card0);
        img = RootView.findViewById(R.id.iv_card0);


        mDataBase = FirebaseDatabase.getInstance().getReference().child("Promo");
        mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(context,"Ошибка доступа", Toast.LENGTH_SHORT).show();
                }
                else {
                    //user = (User)task.getResult().getValue(User.class);
                    //Toast.makeText(context,user.getName(), Toast.LENGTH_SHORT).show();
                    idx = task.getResult().getValue(Integer.class);
                    DBmanager manager = new DBmanager(context);
                    CarCard item = manager.getOneItem(0, idx);
                    String name = item.getName();
                    if(name.contains(" ")){
                        name = name.substring(0, name.indexOf(" "));
                    }
                    brand.setText(item.getBrand() + " " + name);
                    price.setText(String.valueOf(item.getPrice() + "₽"));
                    Picasso.get().load(item.getImage()).into(img);
                }
            }
        });

        cv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBmanager manager = new DBmanager(context);
                CarCard item = manager.getOneItem(0, idx);
                Intent intent = new Intent(context, CarInfo.class);
                intent.putExtra("EXTRA_MESSAGE", item);
                context.startActivity(intent);
            }
        });
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CarCompilation.class);
                intent.putExtra("EXTRA_MESSAGE1", 5);
                intent.putExtra("EXTRA_MESSAGE2", "Внедорожник");
                context.startActivity(intent);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CarCompilation.class);
                intent.putExtra("EXTRA_MESSAGE1", 5);
                intent.putExtra("EXTRA_MESSAGE2", "Седан");
                context.startActivity(intent);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CarCompilation.class);
                intent.putExtra("EXTRA_MESSAGE1", 5);
                intent.putExtra("EXTRA_MESSAGE2", "Спорт классика");
                context.startActivity(intent);
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CarCompilation.class);
                intent.putExtra("EXTRA_MESSAGE1", 11);
                intent.putExtra("EXTRA_MESSAGE2", 6.5f);
                context.startActivity(intent);
            }
        });


        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mDataBase = FirebaseDatabase.getInstance().getReference().child("Promo");
        mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(context,"Ошибка доступа", Toast.LENGTH_SHORT).show();
                }
                else {
                    //user = (User)task.getResult().getValue(User.class);
                    //Toast.makeText(context,user.getName(), Toast.LENGTH_SHORT).show();
                    idx = task.getResult().getValue(Integer.class);
                    DBmanager manager = new DBmanager(context);
                    CarCard item = manager.getOneItem(0, idx);
                    String name = item.getName();
                    if(name.contains(" ")){
                        name = name.substring(0, name.indexOf(" "));
                    }
                    brand.setText(item.getBrand() + " " + name);
                    price.setText(String.format("%,.0f", Float.valueOf(item.getPrice())) + " ₽");
                    Picasso.get().load(item.getImage()).into(img);
                }
            }
        });
    }
}