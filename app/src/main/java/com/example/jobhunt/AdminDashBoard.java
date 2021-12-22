package com.example.jobhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashBoard extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
    }

    public void Logout(View view) {
        auth.signOut();

        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void AdminApplicant(View view) {
        startActivity(new Intent(getApplicationContext(),AdminApplicant.class));
    }

    public void AdminRecruiter(View view) {
        startActivity(new Intent(getApplicationContext(),AdminRecruiter.class));

    }

    public void AdminJobPost(View view) {
        startActivity(new Intent(getApplicationContext(),AdminJobPost.class));

    }
}