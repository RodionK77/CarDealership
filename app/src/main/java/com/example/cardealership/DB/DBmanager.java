package com.example.cardealership.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.cardealership.ui.search.CarCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBmanager {

    Context context;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    Cursor cursor;

    public DBmanager(Context context){
        this.context = context;
        mDBHelper = new DBHelper(this.context);
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

    public List<CarCard> getAllData(int i, String str) {
        List<CarCard> list = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(i).equals(str)){
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
            }
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<CarCard> getAllData(int i, float num) {
        List<CarCard> list = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getFloat(i) <= num && !(cursor.getString(5).equals("Спорт классика"))){
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
            }
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<CarCard> getDifferentData(int i, String str) {
        List<CarCard> list = new ArrayList<>();
        cursor.moveToFirst();
        System.out.println(str);
        for(int k = 0; k < str.length(); k++){
            while (!cursor.isAfterLast()) {
                int num = cursor.getInt(i);
                if(Integer.toString(num).equals(Character.toString(str.charAt(k)))){
                    System.out.println(str.charAt(k) + " " + Integer.toString(num));
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
                }
                cursor.moveToNext();
            }
            cursor.moveToFirst();
        }
        cursor.close();
        return list;
    }

    public CarCard getOneItem(int i, int num) {
        CarCard car = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getInt(i) == num){
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
                car = new CarCard(index, brand, name, price, color, body, capacity, power,
                        transmission, engine, drive, acceleration, consumption, country, car_class, doors,
                        places, wheel, length, width, height, trunk, fuel_tank, image, top);

                break;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return car;
    }
}
