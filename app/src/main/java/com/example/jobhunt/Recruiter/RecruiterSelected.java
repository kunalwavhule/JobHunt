package com.example.jobhunt.Recruiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.jobhunt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecruiterSelected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_selected);

        BottomNavigationView bottomNavigationView = findViewById(R.id.rbottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.selected);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.receive:
                        startActivity(new Intent(getApplicationContext(), ReceiveApplication.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.selected:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), RecruiterDashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), RecruiterProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



    }
}