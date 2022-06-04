package com.example.cardealership.ui.account;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardealership.DB.DBmanager;
import com.example.cardealership.R;
import com.example.cardealership.ui.search.CarCard;
import com.example.cardealership.ui.search.SearchAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.List;

public class Favorites extends AppCompatActivity implements Serializable {

    Toolbar toolbar;
    private User user, user2;
    private String str;
    private RecyclerView carList;
    private SearchAdapter searchAdapter;
    TextView warning;
    List<CarCard> data;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        warning = (TextView) findViewById(R.id.tv_fav_warning);
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        user2 = (User) getIntent().getSerializableExtra("EXTRA_MESSAGE");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
        mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Ошибка доступа", Toast.LENGTH_SHORT).show();
                } else {
                    user = (User) task.getResult().getValue(User.class);
                    String str = user.getBookstores();
                    if(!str.isEmpty()){
                        warning.setVisibility(View.GONE);
                        DBmanager manager = new DBmanager(getApplicationContext());
                        data = manager.getDifferentData(0, str);
                        carList = (RecyclerView) findViewById(R.id.rv_favorites);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        carList.setLayoutManager(layoutManager);
                        carList.setHasFixedSize(true);
                        searchAdapter = new SearchAdapter(data);
                        carList.setAdapter(searchAdapter);
                    } else warning.setVisibility(View.VISIBLE);
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
