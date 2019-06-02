package com.soft.electronicroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btn_navigation;

    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_navigation = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(btn_navigation, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }
}
