package com.example.jobhunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        }

    public void SearchJoB(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void PostJoB(View view) {
        startActivity(new Intent(getApplicationContext(),PostJobActivity.class));
        finish();
    }
}