package com.example.jobhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecruiterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_login);
    }

    public void RecruitersForgetPassword(View view) {

        startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
    }

    public void RedirectToRecruitersRegistration(View view) {
        startActivity(new Intent(getApplicationContext(),RecruiterRegister.class));
        finish();

    }

    public void RecruiterLogin(View view) {

    }
}