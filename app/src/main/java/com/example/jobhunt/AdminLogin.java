package com.example.jobhunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }


    public void RedirectToUserLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}