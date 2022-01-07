package com.example.jobhunt.Applicant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jobhunt.Adapter.ApplicantPostJobAdapter;
import com.example.jobhunt.Login;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class SavedJob extends AppCompatActivity {

    FirebaseAuth auth;
    RecyclerView recyclerView;
    ApplicantPostJobAdapter applicantPostJobAdapter;
    private DatabaseReference mJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_job);
        recyclerView = findViewById(R.id.Aprvsavejob);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth = FirebaseAuth.getInstance();


//        FirebaseDatabase.getInstance().getReference().child("Job Post").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
//                   User user = singleSnapshot.getValue(User.class);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("test", "onCancelled", databaseError.toException());
//            }
//        });



        FirebaseDatabase.getInstance().getReference().child("Job Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                   User user = singleSnapshot.getValue(User.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the da
                Log.d("TAG", "onCancelled: ");
            }
        });
//        FirebaseRecyclerOptions<PostJobData> options =
//                new FirebaseRecyclerOptions.Builder<PostJobData>()
//                        .setQuery(mJob.orderByChild(auth.getUid()).equalTo("2"), PostJobData.class)
//                        .build();
       // applicantPostJobAdapter = new ApplicantPostJobAdapter(options);
        //recyclerView.setAdapter(applicantPostJobAdapter);

        Log.d("TAG", "onCreate: ");
        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.save);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),ApplicantDashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.save:
                        return true;
                    case R.id.appled:
                        startActivity(new Intent(getApplicationContext(),AppliedJob.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
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
        applicantPostJobAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        applicantPostJobAdapter.stopListening();
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