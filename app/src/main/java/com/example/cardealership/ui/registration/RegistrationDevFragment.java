package com.example.cardealership.ui.registration;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cardealership.R;
import com.example.cardealership.ui.ControlFragment;
import com.example.cardealership.ui.account.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationDevFragment extends Fragment {

    EditText etPass, etPass2, etCode, etEmail, etName, etLastName, etPhone, etBirthday;
    boolean passVisible;
    Button btnRegistration;
    private final String adminCode = "54321";
    private FirebaseAuth mAuth;
    DatabaseReference mDataBase;
    Context context;

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
        View RootView = inflater.inflate(R.layout.fragment_registration_dev, container, false);
        etPass = (EditText) RootView.findViewById(R.id.et_dev_password);
        etPass2 = (EditText) RootView.findViewById(R.id.et_dev_password2);
        etCode = (EditText) RootView.findViewById(R.id.et_dev_code);
        etEmail = (EditText) RootView.findViewById(R.id.et_dev_mail);
        etName = (EditText) RootView.findViewById(R.id.et_dev_name);
        etLastName = (EditText) RootView.findViewById(R.id.et_dev_lastname);
        etPhone = (EditText) RootView.findViewById(R.id.et_dev_phone);
        etBirthday = (EditText) RootView.findViewById(R.id.et_dev_date);
        checkPasswordVisible(etPass);
        checkPasswordVisible(etPass2);
        checkPasswordVisible(etCode);

        mAuth = FirebaseAuth.getInstance();
        btnRegistration = RootView.findViewById(R.id.btn_dev_registration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString()) && !TextUtils.isEmpty(etPass2.getText().toString()) &&
                        !TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etLastName.getText().toString()) &&
                        !TextUtils.isEmpty(etPhone.getText().toString()) && !TextUtils.isEmpty(etBirthday.getText().toString()) && !TextUtils.isEmpty(etCode.getText().toString()) ){
                    if(etCode.getText().toString().equals(adminCode)) {
                        if(etPass.getText().toString().equals(etPass2.getText().toString())) {
                            mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(context,"Разработчик успешно зарегестрировался", Toast.LENGTH_SHORT).show();
                                        String name = etName.getText().toString();
                                        String lastname = etLastName.getText().toString();
                                        String email = etEmail.getText().toString();
                                        String birthday = etBirthday.getText().toString();
                                        String phone = etPhone.getText().toString();
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        User newUser = new User(name, lastname, email, birthday, phone, "dev", currentUser.getUid(), "");
                                        mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
                                        mDataBase.setValue(newUser);
                                        ControlFragment.changeFragmentToUser(newUser);
                                    }else Toast.makeText(context,"При регистрации произошла ошибка.\nПопробуйте ещё раз", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else Toast.makeText(context,"Пароли не совпадают", Toast.LENGTH_LONG).show();
                    } else Toast.makeText(context,"Неверный защитный код", Toast.LENGTH_LONG).show();
                }else Toast.makeText(context,"Заполните все поля!", Toast.LENGTH_LONG).show();

            }
        });

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
