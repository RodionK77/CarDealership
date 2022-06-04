package com.example.cardealership.ui.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cardealership.R;
import com.example.cardealership.ui.ControlFragment;

public class RegistrationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_registration, container, false);
        Button btnUser = (Button)RootView.findViewById(R.id.btn_user);
        Button btnAdmin = (Button)RootView.findViewById(R.id.btn_admin);
        Button btnDev = (Button)RootView.findViewById(R.id.btn_dev);
        Button btnToEntrance = (Button)RootView.findViewById(R.id.btn_toEntrance);
        RegistrationUserFragment userFr = new RegistrationUserFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.reg_fr, userFr).commit();

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationUserFragment userFr = new RegistrationUserFragment();
                fragmentManager.beginTransaction().replace(R.id.reg_fr, userFr).commit();
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationAdminFragment userAdmin = new RegistrationAdminFragment();
                fragmentManager.beginTransaction().replace(R.id.reg_fr, userAdmin).commit();
            }
        });
        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationDevFragment userDev = new RegistrationDevFragment();
                fragmentManager.beginTransaction().replace(R.id.reg_fr, userDev).commit();
            }
        });
        btnToEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFragment.changeFragmentToEntrance();
            }
        });
        return RootView;
    }

}
