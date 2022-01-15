package com.example.jobhunt.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Adapter.PostJobAdapter;
import com.example.jobhunt.Login;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminJobPost extends AppCompatActivity {

    RecyclerView recyclerView;
    PostJobAdapter postJobAdapter;
    FirebaseAuth auth;
    private DatabaseReference mJobPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_job_post);
        getSupportActionBar().setTitle("Posted Jobs");

        recyclerView = findViewById(R.id.rvajp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth = FirebaseAuth.getInstance();
        FirebaseUser mUser = auth.getCurrentUser();
        String uid = mUser.getUid();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post");

        FirebaseRecyclerOptions<PostJobData> options =
                new FirebaseRecyclerOptions.Builder<PostJobData>()
                        .setQuery(mJobPost, PostJobData.class)
                        .build();

        postJobAdapter = new PostJobAdapter(options);
        recyclerView.setAdapter(postJobAdapter);




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
        getMenuInflater().inflate(R.menu.search,menu);
        getMenuInflater().inflate(R.menu.logout, menu);
        MenuItem logout = menu.findItem(R.id.lagout);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                return false;
            }
        });
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void txtSearch(String str){

        FirebaseRecyclerOptions<PostJobData> options =
                new FirebaseRecyclerOptions.Builder<PostJobData>()
                        .setQuery(mJobPost.orderByChild("title").startAt(str).endAt(str+"~"), PostJobData.class)
                        .build();
        postJobAdapter = new PostJobAdapter(options);
        postJobAdapter.startListening();
        recyclerView.setAdapter(postJobAdapter);

    }

}