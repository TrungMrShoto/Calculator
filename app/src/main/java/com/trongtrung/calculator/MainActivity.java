package com.trongtrung.calculator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_standard, R.id.nav_programmer, R.id.nav_length,
                R.id.nav_weight, R.id.nav_temp, R.id.nav_speed, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                System.out.println(menuItem.getItemId());
//                switch (menuItem.getItemId())
//                {
//                    case R.id.nav_standard:
//                        Toast.makeText(MainActivity.this,"standard",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_programmer:
//                        Toast.makeText(MainActivity.this,"programmer",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_length:
//                        Toast.makeText(MainActivity.this,"length",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_weight:
//                        Toast.makeText(MainActivity.this,"weight",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_temp:
//                        Toast.makeText(MainActivity.this,"temp",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_speed:
//                        Toast.makeText(MainActivity.this,"speed",Toast.LENGTH_SHORT).show();
//                        break;
//
//                    default:
//                        Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
