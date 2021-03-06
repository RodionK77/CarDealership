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
import com.example.cardealership.ui.Callbacks;
import com.example.cardealership.ui.ControlFragment;
import com.example.cardealership.ui.account.User;
import com.example.cardealership.ui.account.UserFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationUserFragment extends Fragment {

    EditText etPass, etPass2, etEmail, etName, etLastName, etPhone, etBirthday;
    boolean passVisible;
    Button btnRegistration;
    private FirebaseAuth mAuth;
    Context context;
    DatabaseReference mDataBase;
    private Callbacks callbacks = null;

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
        View RootView = inflater.inflate(R.layout.fragment_registration_user, container, false);
        etPass = (EditText) RootView.findViewById(R.id.et_user_password);
        etPass2 = (EditText) RootView.findViewById(R.id.et_user_password2);
        etEmail = (EditText) RootView.findViewById(R.id.et_user_mail);
        etName = (EditText) RootView.findViewById(R.id.et_user_name);
        etLastName = (EditText) RootView.findViewById(R.id.et_user_lastname);
        etPhone = (EditText) RootView.findViewById(R.id.et_user_phone);
        etBirthday = (EditText) RootView.findViewById(R.id.et_user_date);
        checkPasswordVisible(etPass);
        checkPasswordVisible(etPass2);

        mAuth = FirebaseAuth.getInstance();
        btnRegistration = RootView.findViewById(R.id.btn_user_registration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString()) && !TextUtils.isEmpty(etPass2.getText().toString()) &&
                        !TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etLastName.getText().toString()) &&
                        !TextUtils.isEmpty(etPhone.getText().toString()) && !TextUtils.isEmpty(etBirthday.getText().toString())){
                    if(etPass.getText().toString().equals(etPass2.getText().toString())) {
                        mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(context,"???????????????????????? ?????????????? ??????????????????????????????????", Toast.LENGTH_SHORT).show();
                                    String name = etName.getText().toString();
                                    String lastname = etLastName.getText().toString();
                                    String email = etEmail.getText().toString();
                                    String birthday = etBirthday.getText().toString();
                                    String phone = etPhone.getText().toString();
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    User newUser = new User(name, lastname, email, birthday, phone, "user", currentUser.getUid(), "");
                                    mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
                                    mDataBase.setValue(newUser);
                                    UserFragment fr = new UserFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("1", newUser);
                                    fr.setArguments(bundle);
                                    callbacks.controlFragmentSelected(fr);
                                    //ControlFragment.changeFragmentToUser(newUser);
                                }else Toast.makeText(context,"?????? ?????????????????????? ?????????????????? ????????????.\n???????????????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else Toast.makeText(context,"???????????? ???? ??????????????????", Toast.LENGTH_LONG).show();
                }else Toast.makeText(context,"?????????????????? ?????? ????????!", Toast.LENGTH_LONG).show();

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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        callbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

}
