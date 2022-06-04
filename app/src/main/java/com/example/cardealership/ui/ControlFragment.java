package com.example.cardealership.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cardealership.R;
import com.example.cardealership.ui.entrance.EntranceFragment;
import com.example.cardealership.ui.registration.RegistrationFragment;
import com.example.cardealership.ui.account.User;
import com.example.cardealership.ui.account.UserFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class ControlFragment extends Fragment implements Serializable {

    public static FragmentManager fragmentManager;
    Context context;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    FirebaseUser currentUser;
    User user;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_control, container, false);
        RegistrationFragment fr = new RegistrationFragment();
        fragmentManager = getParentFragmentManager();

        //userCheck();

        return RootView;
    }

    public static void changeFragmentToRegistration(){
        RegistrationFragment fr = new RegistrationFragment();
        fragmentManager.beginTransaction().replace(R.id.control_fr, fr).commit();
    }

    public static void changeFragmentToEntrance(){
        EntranceFragment fr = new EntranceFragment();
        fragmentManager.beginTransaction().replace(R.id.control_fr, fr).commit();
    }
    public static void changeFragmentToUser(User user){
        UserFragment fr = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("1", user);
        fr.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.control_fr, fr).commit();
    }

    public void userCheck(){
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
            mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(context,"Ошибка доступа", Toast.LENGTH_SHORT).show();
                        changeFragmentToRegistration();
                    }
                    else {
                        user = (User)task.getResult().getValue(User.class);
                        //Toast.makeText(context,user.getName(), Toast.LENGTH_SHORT).show();
                        changeFragmentToUser(user);
                    }
                }
            });
            //getDataFromDB();
            //Toast.makeText(context,user.getUid(), Toast.LENGTH_SHORT).show();
            //changeFragmentToUser(user);
        } else {
            changeFragmentToRegistration();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        userCheck();
    }
}


