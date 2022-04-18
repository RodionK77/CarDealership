package com.example.cardealership;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cardealership.ui.home.HomeFragment;
import com.example.cardealership.ui.search.SearchFragment;
import com.example.cardealership.ui.user.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cardealership.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        viewPager2=findViewById(R.id.pager);
        pagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setPageTransformer(new MyViewPagerAdapter.ZoomOutPageTransformer());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_loupe, R.id.navigation_user)
                .build();
        navView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager2.setCurrentItem(0);
                                break;
                            case R.id.navigation_loupe:
                                viewPager2.setCurrentItem(1);
                                break;
                            case R.id.navigation_user:
                                viewPager2.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        navView.getMenu().getItem(position).setChecked(true);
                        break;
                    case 1:
                        navView.getMenu().getItem(position).setChecked(true);
                        break;
                    case 2:
                        navView.getMenu().getItem(position).setChecked(true);
                        break;
                }
            }
        });

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
    }

}