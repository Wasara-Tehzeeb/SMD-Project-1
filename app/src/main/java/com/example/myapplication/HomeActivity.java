package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;
    private static final String PREF_NAME = "cinefast_session_pref_v3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        updateNavHeader();

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            navView.setCheckedItem(R.id.nav_home);
        }

        navView.setNavigationItemSelectedListener(this);
    }
    private void updateNavHeader() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            View headerView = navView.getHeaderView(0);
            TextView tvName = headerView.findViewById(R.id.navHeaderName);
            TextView tvEmail = headerView.findViewById(R.id.navHeaderEmail);

            String name = user.getDisplayName();
            if (name != null && !name.isEmpty()) {
                tvName.setText(name);
            } else {
                tvName.setText("CineFAST User");
            }
            tvEmail.setText(user.getEmail());
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            loadFragment(new HomeFragment());
        }
        else if (id == R.id.nav_my_bookings) {
            loadFragment(new MyBookingsFragment());
        }
        else if (id == R.id.nav_logout) {
            handleLogout();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleLogout() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}