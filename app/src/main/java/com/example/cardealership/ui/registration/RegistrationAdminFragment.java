package com.example.cardealership.ui.registration;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.cardealership.R;

public class RegistrationAdminFragment extends Fragment {

    EditText pass, pass2, code;
    boolean passVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_registration_admin, container, false);
        pass = (EditText) RootView.findViewById(R.id.et_user_password);
        pass2 = (EditText) RootView.findViewById(R.id.et_user_password2);
        code = (EditText) RootView.findViewById(R.id.et_user_code);
        checkPasswordVisible(pass);
        checkPasswordVisible(pass2);
        checkPasswordVisible(code);
        return RootView;
    }

    public void checkPasswordVisible(EditText pass) {
        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= pass.getRight() - pass.getCompoundDrawables()[right].getBounds().width()) {
                        int selection = pass.getSelectionEnd();
                        if (passVisible) {
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0);
                            pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            pass.setInputType(129);
                            passVisible = false;
                        } else {
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                            pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            pass.setInputType(129);
                            pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            passVisible = true;
                        }
                        pass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
    }

}

