package com.example.cardealership.ui.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardealership.DB.DBmanager;
import com.example.cardealership.R;
import com.example.cardealership.ui.search.CarCard;
import com.example.cardealership.ui.search.SearchAdapter;

import java.io.Serializable;
import java.util.List;

public class CarCompilation extends AppCompatActivity implements Serializable {

    Toolbar toolbar;
    private int i;
    private float num;
    private String str = null;
    private RecyclerView carList;
    private SearchAdapter searchAdapter;
    List<CarCard> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compilation);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        i = getIntent().getIntExtra("EXTRA_MESSAGE1", 0);
        str = getIntent().getStringExtra("EXTRA_MESSAGE2");
        if(str == null){
            num = getIntent().getFloatExtra("EXTRA_MESSAGE2", 0);
            toolbar.setTitle("Подборка " + '"' + "Для души" + '"');
            System.out.println(i + " " + num);
            DBmanager manager = new DBmanager(this);
            data = manager.getAllData(i, num);
        }else{
            toolbar.setTitle("Подборка " + '"' + str + '"');
            DBmanager manager = new DBmanager(this);
            data = manager.getAllData(i, str);
        }
        carList = (RecyclerView) findViewById(R.id.rv_compilation);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        carList.setLayoutManager(layoutManager);
        carList.setHasFixedSize(true);
        searchAdapter = new SearchAdapter(data);
        carList.setAdapter(searchAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

}
