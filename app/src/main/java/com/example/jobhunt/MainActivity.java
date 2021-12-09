package com.example.jobhunt;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameconter,new HomeFragment()).commit();
        bnv = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId()){
                    case R.id.menu_search:temp=new HomeFragment();
                    break;
                    case R.id.menu_profile:temp=new ProfileFragment();
                        break;
                    case R.id.menu_save:temp=new BookmarkFragment();
                        break;
                    case R.id.menu_msg:temp=new NotificationFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameconter,temp).commit();
                return true;
            }
        });

    }


    public void Logout(View view) {
        auth.signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
}