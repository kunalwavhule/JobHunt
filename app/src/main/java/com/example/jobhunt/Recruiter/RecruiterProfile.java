package com.example.jobhunt.Recruiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobhunt.Login;
import com.example.jobhunt.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class RecruiterProfile extends AppCompatActivity {
    TextView name,phoneno,email,companyaddress,companytypes,companydesc,companyopeninghours;
    DatabaseReference mDatabase;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_profile);
        fab = findViewById(R.id.editprofile);
        name = findViewById(R.id.proname);
        phoneno = findViewById(R.id.phoneno);
        email = findViewById(R.id.email);
        companyaddress = findViewById(R.id.companyaddress);
        companytypes = findViewById(R.id.companytypes);
        companydesc = findViewById(R.id.companydesc);
        companyopeninghours = findViewById(R.id.companyopeninghours);
        getSupportActionBar().setTitle("Profile");


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
                String emails = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("email").getValue(String.class);
                String RcompanyOpeninghours = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companyaddress").getValue(String.class);
                String RcompanyAddress = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companyopeninghours").getValue(String.class);
                String RcompanyTypes = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companytypes").getValue(String.class);
                String RcompanyDesc = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companydesc").getValue(String.class);
                name.setText(proname);
                phoneno.setText(phone_no);
                email.setText(emails);
                companytypes.setText(RcompanyTypes);
                companyaddress.setText(RcompanyAddress);
                companydesc.setText(RcompanyDesc);
                companyopeninghours.setText(RcompanyOpeninghours);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        mDatabase.addValueEventListener(postListener);
    }


    public void Profile(View view) {
        final DialogPlus dialogPlus = DialogPlus.newDialog(fab.getContext())
                .setContentHolder(new ViewHolder(R.layout.recruiter_profile_edit_item))
                .setExpanded(true,1500)
                .create();
        View view1 = dialogPlus.getHolderView();
        TextInputEditText name = view1.findViewById(R.id.profilename);
        TextInputEditText phoneno = view1.findViewById(R.id.profile_no);
        TextInputEditText companyadr = view1.findViewById(R.id.companyaddress);
        TextInputEditText companytyp = view1.findViewById(R.id.companytypes);
        TextInputEditText companydesc = view1.findViewById(R.id.companydesc);
        TextInputEditText companyhr = view1.findViewById(R.id.companyopeninghours);
        Button btnUpdate = view1.findViewById(R.id.button);
        name.setText(name.getText().toString());
        phoneno.setText(phoneno.getText().toString());
        companyadr.setText(companyadr.getText().toString());
        companytyp.setText(companytyp.getText().toString());
        companydesc.setText(companydesc.getText().toString());
        companyhr.setText(companyhr.getText());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String proname = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("fullname").getValue(String.class);
                String phone_no = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("phoneno").getValue(String.class);
                String emails = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("email").getValue(String.class);
                String RcompanyOpeninghours = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companyaddress").getValue(String.class);
                String RcompanyAddress = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companyopeninghours").getValue(String.class);
                String RcompanyTypes = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companytypes").getValue(String.class);
                String RcompanyDesc = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("companydesc").getValue(String.class);
                name.setText(proname);
                phoneno.setText(phone_no);
                email.setText(emails);
                companytypes.setText(RcompanyTypes);
                companyaddress.setText(RcompanyAddress);
                companydesc.setText(RcompanyDesc);
                companyopeninghours.setText(RcompanyOpeninghours);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        mDatabase.addValueEventListener(postListener);



        dialogPlus.show();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<>();
                map.put("fullname",name.getText().toString());
                map.put("phoneno",phoneno.getText().toString());
                map.put("companytypes",companytyp.getText().toString());
                map.put("companyaddress",companyadr.getText().toString());
                map.put("companydesc",companydesc.getText().toString());
                map.put("companyopeninghours",companyhr.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(name.getContext(),"data is updated",Toast.LENGTH_LONG).show();
                        dialogPlus.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(name.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
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