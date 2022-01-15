package com.example.jobhunt.Recruiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jobhunt.Login;
import com.example.jobhunt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecruiterProfile extends AppCompatActivity {
    ImageButton imageButton;
    TextView name,phoneno,proemail,companyaddress,companytypes,companydesc,companyopeninghours;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_profile);
        imageButton = findViewById(R.id.imageButton4);
        name = findViewById(R.id.proname);
        phoneno = findViewById(R.id.prophoneno);
        proemail = findViewById(R.id.proemail);

        BottomNavigationView bottomNavigationView = findViewById(R.id.rbottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.rprofile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), RecruiterDashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.rprofile:
                        return true;
                    case R.id.receive:
                        startActivity(new Intent(getApplicationContext(),ReceiveApplication.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        mDatabase = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String proname = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("fullname").getValue(String.class);
                String phone_no = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("phoneno").getValue(String.class);
                String email = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("email").getValue(String.class);
                String companyOpeninghours = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companyaddress").getValue(String.class);
                String companyAddress = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companyopeninghours").getValue(String.class);
                String companyTypes = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companytypes").getValue(String.class);
                String companyDesc = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companydesc").getValue(String.class);
                name.setText(proname);
                phoneno.setText("phone number: \t"+phone_no);
                proemail.setText("email: \t\b"+email);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        mDatabase.addValueEventListener(postListener);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        MenuItem logoutitem = menu.findItem(R.id.lagout);
        logoutitem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}