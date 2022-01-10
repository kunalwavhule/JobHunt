package com.example.jobhunt.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.jobhunt.Adapter.UserAdapter;
import com.example.jobhunt.Login;
import com.example.jobhunt.Model.Data;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminApplicant extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    FirebaseAuth auth;
    private DatabaseReference mJobPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Applicants");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_applicant);
        recyclerView = findViewById(R.id.rvaa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth = FirebaseAuth.getInstance();
        FirebaseUser mUser = auth.getCurrentUser();
        String uid = mUser.getUid();
        mJobPost = FirebaseDatabase.getInstance().getReference().child("User");
        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mJobPost.orderByChild("userTypes").equalTo(0), Data.class)
                        .build();
        userAdapter = new UserAdapter(options);
        recyclerView.setAdapter(userAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        userAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        userAdapter.stopListening();
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