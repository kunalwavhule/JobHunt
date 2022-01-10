package com.example.jobhunt.Recruiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jobhunt.Adapter.PostJobAdapter;
import com.example.jobhunt.Adapter.ReceiveAppliationAdapter;
import com.example.jobhunt.Login;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.Model.ReceiveApplicationData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReceiveApplication extends AppCompatActivity {

    RecyclerView recyclerView;
    ReceiveAppliationAdapter receiveAppliationAdapter;
    FirebaseAuth auth;
    private DatabaseReference mJobPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Recieved Job Application");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_application);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        auth = FirebaseAuth.getInstance();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("Applied").child(auth.getUid());
        FirebaseRecyclerOptions<ReceiveApplicationData> options =
                new FirebaseRecyclerOptions.Builder<ReceiveApplicationData>()
                        .setQuery(mJobPost, ReceiveApplicationData.class)
                        .build();
        receiveAppliationAdapter = new ReceiveAppliationAdapter(options);
        recyclerView.setAdapter(receiveAppliationAdapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.rbottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.receive);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), RecruiterDashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.receive:
                        return true;
                    case R.id.selected:
                        startActivity(new Intent(getApplicationContext(), RecruiterSelected.class));
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

    @Override
    protected void onStart() {
        super.onStart();
        receiveAppliationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        receiveAppliationAdapter.stopListening();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        MenuItem logoutitem = menu.findItem(R.id.lagout);
        logoutitem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
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