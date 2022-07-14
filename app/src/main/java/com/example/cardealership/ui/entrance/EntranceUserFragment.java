package com.example.cardealership.ui.entrance;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EntranceUserFragment extends Fragment {

    Context context;
    EditText etPass, etEmail;
    boolean passVisible;
    Button btnEnter;
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;
    User user;
    FirebaseUser currentUser;
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
        View RootView = inflater.inflate(R.layout.fragment_entrance_user, container, false);
        etPass = (EditText) RootView.findViewById(R.id.et_user_password_enter);
        etEmail = (EditText) RootView.findViewById(R.id.et_user_mail_enter);
        checkPasswordVisible(etPass);

        mAuth = FirebaseAuth.getInstance();
        btnEnter = RootView.findViewById(R.id.btn_user_enter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString())){
                    mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString()).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                currentUser = mAuth.getCurrentUser();
                                mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
                                mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(context,"Ошибка доступа", Toast.LENGTH_SHORT).show();
                                            EntranceFragment fr = new EntranceFragment();
                                            callbacks.controlFragmentSelected(fr);
                                            //ControlFragment.changeFragmentToEntrance();
                                        }
                                        else {
                                            user = (User)task.getResult().getValue(User.class);
                                            if(user.getRole().equals("user")) {
                                                //Toast.makeText(context,user.getName(), Toast.LENGTH_SHORT).show();
                                                Toast.makeText(context,"Пользователь зашёл", Toast.LENGTH_SHORT).show();
                                                UserFragment fr = new UserFragment();
                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable("1", user);
                                                fr.setArguments(bundle);
                                                callbacks.controlFragmentSelected(fr);
                                                //ControlFragment.changeFragmentToUser(user);
                                            }else {
                                                Toast.makeText(context,"Вы не являетесь обычным пользователем", Toast.LENGTH_SHORT).show();
                                                FirebaseAuth.getInstance().signOut();
                                                EntranceFragment fr = new EntranceFragment();
                                                callbacks.controlFragmentSelected(fr);
                                                //ControlFragment.changeFragmentToEntrance();
                                            }
                                        }
                                    }
                                });
                            }else Toast.makeText(context,"Вход неудался.\nПопробуйте ещё раз", Toast.LENGTH_SHORT).show();
                        }
                    });
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

