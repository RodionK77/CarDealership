package com.example.cardealership.ui.search;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardealership.DB.DBmanager;
import com.example.cardealership.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchFragment extends Fragment {

    Context context;
    private RecyclerView carList;
    private SearchAdapter searchAdapter;
    List<CarCard> data;
    //TextView textView;
    //Button btn;
    Button btn1, btn2, btn3;
    BottomNavigationView bv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_search, container, false);
        btn1 = RootView.findViewById(R.id.btn_sort1);
        btn2 = RootView.findViewById(R.id.btn_sort2);
        btn3 = RootView.findViewById(R.id.btn_sort3);

        DBmanager manager = new DBmanager(context);
        data = manager.getAllData();

        carList = (RecyclerView) RootView.findViewById(R.id.rv_cars);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        carList.setLayoutManager(layoutManager);
        carList.setHasFixedSize(true);
        searchAdapter = new SearchAdapter(data);
        carList.setAdapter(searchAdapter);

        //btn = (Button)RootView.findViewById(R.id.button);
        //textView = (TextView)RootView.findViewById(R.id.textView);

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = "";

                Cursor cursor = mDb.rawQuery("SELECT * FROM Cars", null);
                String[] columnNames = cursor.getColumnNames();
                cursor.moveToFirst();
                product += columnNames.length;
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(24) + " | ";
                    cursor.moveToNext();
                }
                cursor.close();

                textView.setText(product);

            }
        });*/

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(data, new Comparator<CarCard>() {
                    @Override
                    public int compare(CarCard lhs, CarCard rhs) {
                        return Integer.compare( lhs.getPrice(), rhs.getPrice());
                    }
                });
                searchAdapter = new SearchAdapter(data);
                carList.setAdapter(searchAdapter);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(data, new Comparator<CarCard>() {
                    @Override
                    public int compare(CarCard lhs, CarCard rhs) {
                        return Integer.compare( rhs.getPrice(), lhs.getPrice());
                    }
                });
                searchAdapter = new SearchAdapter(data);
                carList.setAdapter(searchAdapter);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comparator<CarCard> carsComparator = Comparator.comparing(CarCard::getBrand);
                Collections.sort(data, carsComparator);
                searchAdapter = new SearchAdapter(data);
                carList.setAdapter(searchAdapter);
            }
        });

        return RootView;
    }

}