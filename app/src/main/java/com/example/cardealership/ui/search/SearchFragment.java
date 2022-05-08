package com.example.cardealership.ui.search;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardealership.DBHelper;
import com.example.cardealership.R;
import com.example.cardealership.databinding.FragmentSearchBinding;
import com.example.cardealership.ui.registration.RegistrationUserFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    Context context;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    Cursor cursor;
    private RecyclerView carList;
    private SearchAdapter searchAdapter;
    List<CarCard> data;
    //TextView textView;
    //Button btn;

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
        View RootView = inflater.inflate(R.layout.fragment_search, container, false);

        mDBHelper = new DBHelper(context);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        cursor = mDb.rawQuery("SELECT * FROM Cars", null);
        data = getAllData();

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

        return RootView;
    }

    public List<CarCard> getAllData() {
        List<CarCard> list = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int index = cursor.getInt(0);
            String brand = cursor.getString(1);
            String name= cursor.getString(2);
            int price = cursor.getInt(3);
            String color = cursor.getString(4);
            String body = cursor.getString(5);
            float capacity = cursor.getFloat(6);
            int power = cursor.getInt(7);
            String transmission = cursor.getString(8);
            String engine = cursor.getString(9);
            String drive = cursor.getString(10);
            float acceleration = cursor.getFloat(11);
            float consumption = cursor.getFloat(12);
            String country = cursor.getString(13);
            String car_class = cursor.getString(14);
            int doors = cursor.getInt(15);
            int places = cursor.getInt(16);
            String wheel = cursor.getString(17);
            int length = cursor.getInt(18);
            int width = cursor.getInt(19);
            int height = cursor.getInt(20);
            int trunk = cursor.getInt(21);
            int fuel_tank = cursor.getInt(22);
            String image = cursor.getString(23);
            int top = cursor.getInt(24);
            CarCard car = new CarCard(index, brand, name, price, color, body, capacity, power,
                    transmission, engine, drive, acceleration, consumption, country, car_class, doors,
                    places, wheel, length, width, height, trunk, fuel_tank, image, top);
            list.add(car);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}