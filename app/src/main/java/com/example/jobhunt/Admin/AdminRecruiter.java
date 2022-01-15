package com.example.jobhunt.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Adapter.RecruiterAdapter;
import com.example.jobhunt.Adapter.UserAdapter;
import com.example.jobhunt.Login;
import com.example.jobhunt.Model.Data;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminRecruiter extends AppCompatActivity {

    RecyclerView recyclerView;
    RecruiterAdapter recruiterAdapter;
    FirebaseAuth auth;
    private DatabaseReference mJobPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_recruiter);
        getSupportActionBar().setTitle("Recruiter Data");


        recyclerView = findViewById(R.id.rvar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        auth = FirebaseAuth.getInstance();
        FirebaseUser mUser = auth.getCurrentUser();
        String uid = mUser.getUid();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("User");

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mJobPost.orderByChild("userTypes").equalTo(1), Data.class)
                        .build();

        recruiterAdapter = new RecruiterAdapter(options);
        recyclerView.setAdapter(recruiterAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();
        recruiterAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recruiterAdapter.stopListening();
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
