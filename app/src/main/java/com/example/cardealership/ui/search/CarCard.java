package com.example.cardealership.ui.search;

import java.io.Serializable;

public class CarCard implements Serializable {
    protected int index;
    protected String brand;
    protected String name;
    protected int price;
    protected String color;
    protected String body;
    protected float capacity;
    protected int power;
    protected String transmission;
    protected String engine;
    protected String drive;
    protected float acceleration;
    protected float consumption;
    protected String country;
    protected String car_class;
    protected int doors;
    protected int places;
    protected String wheel;
    protected int length;
    protected int width;
    protected int height;
    protected int trunk;
    protected int fuel_tank;
    protected String image;
    protected int top;

    public CarCard(int index, String brand, String name, int price, String color, String body, float capacity,
                   int power, String transmission, String engine, String drive, float acceleration,
                   float consumption, String country, String car_class, int doors, int places, String wheel,
                   int length, int width, int height, int trunk, int fuel_tank, String image, int top){
        this.index = index;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.color = color;
        this.body = body;
        this.capacity = capacity;
        this.power = power;
        this.transmission = transmission;
        this.engine = engine;
        this.drive = drive;
        this.acceleration = acceleration;
        this.consumption = consumption;
        this.country = country;
        this.car_class = car_class;
        this.doors = doors;
        this.places = places;
        this.wheel = wheel;
        this.length = length;
        this.width = width;
        this.height = height;
        this.trunk = trunk;
        this.fuel_tank = fuel_tank;
        this.image = image;
        this.top = top;
    }

    public int getIndex() {
        return index;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getBody() {
        return body;
    }

    public float getCapacity() {
        return capacity;
    }

    public int getPower() {
        return power;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getEngine() {
        return engine;
    }

    public String getDrive() {
        return drive;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getConsumption() {
        return consumption;
    }

    public String getCountry() {
        return country;
    }

    public String getCar_class() {
        return car_class;
    }

    public int getDoors() {
        return doors;
    }

    public int getPlaces() {
        return places;
    }

    public String getWheel() {
        return wheel;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTrunk() {
        return trunk;
    }

    public int getFuel_tank() {
        return fuel_tank;
    }

    public String getImage() {
        return image;
    }

    public int getTop() {
        return top;
    }
}
