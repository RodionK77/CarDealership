package com.example.cardealership.ui.entrance;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cardealership.ui.Callbacks;
import com.example.cardealership.R;
import com.example.cardealership.ui.ControlFragment;

public class EntranceFragment extends Fragment {

    private Callbacks callbacks = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_entrance, container, false);
        Button btnUser = (Button)RootView.findViewById(R.id.btn_user);
        Button btnAdmin = (Button)RootView.findViewById(R.id.btn_admin);
        Button btnDev = (Button)RootView.findViewById(R.id.btn_dev);
        Button btnToRegistration = (Button)RootView.findViewById(R.id.btn_toRegistration);
        EntranceUserFragment userFr = new EntranceUserFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.entr_fr, userFr).commit();

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntranceUserFragment userFr = new EntranceUserFragment();
                fragmentManager.beginTransaction().replace(R.id.entr_fr, userFr).commit();
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntranceAdminFragment userAdmin = new EntranceAdminFragment();
                fragmentManager.beginTransaction().replace(R.id.entr_fr, userAdmin).commit();
            }
        });
        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntranceDevFragment userDev = new EntranceDevFragment();
                fragmentManager.beginTransaction().replace(R.id.entr_fr, userDev).commit();
            }
        });
        btnToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFragment fr = new ControlFragment();
                callbacks.controlFragmentSelected(fr);
                //ControlFragment.changeFragmentToRegistration();
            }
        });
        return RootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

}
