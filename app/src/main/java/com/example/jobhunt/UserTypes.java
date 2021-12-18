package com.example.jobhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserTypes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_types);
    }

    // Redirect to Admin Login page
    public void RedirectToAdminLogin(View view) {
        startActivity(new Intent(getApplicationContext(),AdminLogin.class));
    }

    // Redirect to Recruiters Login page
    public void RedirectToRecruitersLogin(View view) {
        startActivity(new Intent(getApplicationContext(),RecruiterLogin.class));

    }

    // Redirect to User Login page
    public void RedirectToUserLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
}