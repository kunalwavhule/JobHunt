package com.example.jobhunt.Admin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jobhunt.Admin.AdminApplicant;
import com.example.jobhunt.Admin.AdminJobPost;
import com.example.jobhunt.Admin.AdminRecruiter;
import com.example.jobhunt.Login;
import com.example.jobhunt.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashBoard extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
    }


    public void AdminApplicant(View view) {
        startActivity(new Intent(getApplicationContext(), AdminApplicant.class));
    }

    public void AdminRecruiter(View view) {
        startActivity(new Intent(getApplicationContext(), AdminRecruiter.class));

    }

    public void AdminJobPost(View view) {
        startActivity(new Intent(getApplicationContext(), AdminJobPost.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        MenuItem logout = menu.findItem(R.id.lagout);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
