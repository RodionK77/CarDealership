package com.example.cardealership.ui;

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
import com.example.cardealership.ui.entrance.EntranceFragment;
import com.example.cardealership.ui.registration.RegistrationAdminFragment;
import com.example.cardealership.ui.registration.RegistrationFragment;
import com.example.cardealership.ui.registration.RegistrationUserFragment;

public class ControlFragment extends Fragment {

    public static FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_control, container, false);
        RegistrationFragment fr = new RegistrationFragment();
        fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.control_fr, fr).commit();
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
}


