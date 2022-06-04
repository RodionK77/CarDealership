package com.example.cardealership.ui.account;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardealership.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsersList extends AppCompatActivity implements Serializable {
    Toolbar toolbar;
    private String str;
    private RecyclerView userList;
    private UsersAdapter usersAdapter;
    List<User> data = new ArrayList<>();
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance().getReference("Users");

        init();
        getAllData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

    private void init(){
        userList = (RecyclerView) findViewById(R.id.rv_users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        userList.setLayoutManager(layoutManager);
        userList.setHasFixedSize(true);
        usersAdapter = new UsersAdapter(data);
        userList.setAdapter(usersAdapter);
    }

    private void getAllData(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(data.size() > 0){ data.clear(); }
                for(DataSnapshot ds : snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    assert user != null;
                    data.add(user);
                    System.out.println(user.getName());
                }
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(valueEventListener);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        User user = data.get(item.getItemId());
        if(!(currentUser.getUid().equals(user.getUid()))) {
            if (user.getRole().equals("admin") && item.getTitle().equals("Пользователя")) {
                user.setRole("user");
            } else if (user.getRole().equals("admin") && item.getTitle().equals("Разработчика")) {
                user.setRole("dev");
            }else if (user.getRole().equals("user") && item.getTitle().equals("Администратора")) {
                user.setRole("admin");
            }else if (user.getRole().equals("user") && item.getTitle().equals("Разработчика")) {
                user.setRole("dev");
            }else if (user.getRole().equals("dev") && item.getTitle().equals("Пользователя")) {
                user.setRole("user");
            }else if (user.getRole().equals("dev") && item.getTitle().equals("Администратора")) {
                user.setRole("admin");
            }
            mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
            mDataBase.setValue(user);
        }else Toast.makeText(getApplicationContext(),"Вы не можете изменить свою роль", Toast.LENGTH_SHORT).show();

        return super.onContextItemSelected(item);
    }
}
