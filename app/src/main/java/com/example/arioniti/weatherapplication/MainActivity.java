package com.example.arioniti.weatherapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.arioniti.weatherapplication.db.DataSource;

public class MainActivity extends AppCompatActivity {

    private static final String HOME_FRAGMENT = "HOME_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize HomeFragment
        Fragment homeFragment = getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);

        //Check if HomeFragment is null
        if (homeFragment == null) {
            HomeFragment firstFragment = new HomeFragment();
            // Add Fragment to FrameLayout (flContainer), using FragmentManager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            ft.add(R.id.flContainer, firstFragment, HOME_FRAGMENT);
            // add    Fragment
            ft.commit();
        }

        new DataSource(this);
    }

    public void onClick(View v) {
        FiveDaysWeatherFragment firstFragment = new FiveDaysWeatherFragment();
        // Add Fragment to FrameLayout (flContainer), using FragmentManager
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainer, firstFragment) // replace flContainer
                .addToBackStack(null)
                .commit();
    }
}

