package com.example.cardealership.ui.account;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String name, lastname, email, birthday, phone, role, uid;
    String bookstores = "";

    public User(){

    }

    public User(String name, String lastname, String email, String birthday, String phone, String role, String uid, String bookstores) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
        this.uid = uid;
        this.bookstores = bookstores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBookstores(){
        return bookstores;
    }

    public void setBookstores(String bookstores){
        this.bookstores = bookstores;
    }

    public void addBookstores(int num){
        bookstores += Integer.toString(num);
    }

    public void delBookstores(int num){
        bookstores = bookstores.replace(Integer.toString(num), "");
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
