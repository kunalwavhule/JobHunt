package com.example.jobhunt.Recruiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.jobhunt.Adapter.PostJobAdapter;
import com.example.jobhunt.Login;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecruiterDashboard extends AppCompatActivity {
    FloatingActionButton fabBtn;
    // recycler view
    RecyclerView recyclerView;
    PostJobAdapter postJobAdapter;
    FirebaseAuth auth;
    private DatabaseReference mJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_dashboard);
        fabBtn = findViewById(R.id.fab_add);
        recyclerView = findViewById(R.id.recycler_job_post_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        auth = FirebaseAuth.getInstance();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post");
        FirebaseRecyclerOptions<PostJobData> options =
                new FirebaseRecyclerOptions.Builder<PostJobData>()
                        .setQuery(mJobPost.orderByChild("id").equalTo(auth.getUid()), PostJobData.class)
                        .build();
        postJobAdapter = new PostJobAdapter(options);
        recyclerView.setAdapter(postJobAdapter);
        // recyclerview
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InsertJobPostActivity.class));
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.rbottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.receive:
                        startActivity(new Intent(getApplicationContext(), ReceiveApplication.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
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
        postJobAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        postJobAdapter.stopListening();
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

    public void ReceiveApplication(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiveApplication.class));
    }
}
