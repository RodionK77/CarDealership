package com.example.cardealership.ui.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.cardealership.R;
import com.example.cardealership.databinding.FragmentUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class UserFragment extends Fragment implements Serializable {

    private FragmentUserBinding binding;
    TextView name;
    Button ok;
    CardView fav, list_users, settings;
    Context context;
    TextView role;
    LinearLayout ll, dev_ll;
    User user;
    FirebaseUser currentUser;
    EditText et_id;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

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
        View RootView = inflater.inflate(R.layout.fragment_user, container, false);
        //userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("1");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
        mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(context,"Ошибка доступа", Toast.LENGTH_SHORT).show();
                }
                else {
                    user = (User)task.getResult().getValue(User.class);
                }
            }
        });

        settings = (CardView)  RootView.findViewById(R.id.cv_btn3);
        list_users = (CardView)  RootView.findViewById(R.id.cv_btn4);
        //btn = (Button) RootView.findViewById(R.id.btn_exit);
        role = (TextView)RootView.findViewById(R.id.tv_role);
        fav = (CardView) RootView.findViewById(R.id.cv_btn1);
        ll = (LinearLayout) RootView.findViewById(R.id.ll_admin_users);
        name = (TextView) RootView.findViewById(R.id.tv_user_card_name);
        dev_ll = RootView.findViewById(R.id.ll_dev);
        ok = RootView.findViewById(R.id.btn_dev_ok);
        et_id = RootView.findViewById(R.id.et_dev_id);
        if(user.getRole().equals("admin")){
            role.setVisibility(View.VISIBLE);
            ll.setVisibility(View.VISIBLE);
        }
        if(user.getRole().equals("dev")){
            role.setVisibility(View.VISIBLE);
            role.setText("Разработчик");
            dev_ll.setVisibility(View.VISIBLE);
            ok.setVisibility(View.VISIBLE);
        }
        String nm = user.getName() + " " + user.getLastname();
        name.setText(nm);

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(context,"Пользователь вышел из аккаунта", Toast.LENGTH_SHORT).show();
                ControlFragment.changeFragmentToEntrance();
            }
        });*/

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Settings.class);
                context.startActivity(intent);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Favorites.class);
                intent.putExtra("EXTRA_MESSAGE", user);
                context.startActivity(intent);
            }
        });

        list_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UsersList.class);
                context.startActivity(intent);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = et_id.getText().toString();
                int id = Integer.parseInt(value);
                if(!(id > 18 || id < 1) ){
                    mDataBase = FirebaseDatabase.getInstance().getReference().child("Promo");
                    mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(context,"Ошибка доступа", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mDataBase.setValue(id);
                            }
                        }
                    });

                }else Toast.makeText(context,"Неверный ID", Toast.LENGTH_SHORT).show();
            }
        });


        //binding = FragmentUserBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            mDataBase = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
            mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(context, "Ошибка доступа", Toast.LENGTH_SHORT).show();
                    } else {
                        user = (User) task.getResult().getValue(User.class);
                    }
                }
            });

            name.setText(user.getName() + " " + user.getLastname());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}