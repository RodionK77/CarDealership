package com.example.cardealership.ui.account;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cardealership.R;
import com.example.cardealership.ui.ControlFragment;
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

public class Settings extends AppCompatActivity implements Serializable {

    Toolbar toolbar;
    EditText etPass, etPass2, etEmail, etName, etLastName, etPhone, etBirthday;
    Button exit;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar_s);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etEmail = (EditText) findViewById(R.id.et_settings_mail);
        etName = (EditText) findViewById(R.id.et_settings_name);
        etLastName = (EditText) findViewById(R.id.et_settings_lastname);
        etPhone = (EditText) findViewById(R.id.et_settings_phone);
        etBirthday = (EditText) findViewById(R.id.et_settings_date);
        exit = (Button) findViewById(R.id.btn_exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"Пользователь вышел из аккаунта", Toast.LENGTH_SHORT).show();
                finish();
                //ControlFragment.changeFragmentToEntrance();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
        mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Ошибка доступа", Toast.LENGTH_SHORT).show();
                }
                else {
                    user = (User)task.getResult().getValue(User.class);
                    etEmail.setText(user.getEmail());
                    etName.setText(user.getName());
                    etLastName.setText(user.getLastname());
                    etPhone.setText(user.getPhone());
                    etBirthday.setText(user.getBirthday());
                }
            }
        });

    }

    public void onPush(View view){
        user.setName(etName.getText().toString());
        user.setLastname(etLastName.getText().toString());
        user.setBirthday(etBirthday.getText().toString());
        user.setEmail(etEmail.getText().toString());
        user.setPhone(etPhone.getText().toString());
        mDataBase.setValue(user);
        Toast.makeText(getApplicationContext(),"Изменения внесены", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

}
