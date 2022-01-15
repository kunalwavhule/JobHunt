package com.example.jobhunt.Applicant;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobhunt.Login;
import com.example.jobhunt.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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

public class Profile extends AppCompatActivity {
    ImageButton imageButton;
    TextView jobprofile,company,jobDescription,expDuration,name,phoneno,proemail,eduDec;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Applicant Profile");
        imageButton = findViewById(R.id.imageButton4);
        jobprofile = findViewById(R.id.jobname);
        company = findViewById(R.id.company);
        jobDescription = findViewById(R.id.role);
        expDuration = findViewById(R.id.ExpDuration);
        name = findViewById(R.id.proname);
        phoneno = findViewById(R.id.prophoneno);
        proemail = findViewById(R.id.proemail);
        eduDec = findViewById(R.id.education);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String JobProfile = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobrole").getValue(String.class);
                String proname = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("fullname").getValue(String.class);
                String phone_no = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("phoneno").getValue(String.class);
                String Company = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantcompany").getValue(String.class);
                String job_Decription = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobdescription").getValue(String.class);
                String expduration = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantexperience").getValue(String.class);
                String edu_dec = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicanteducationdesc").getValue(String.class);
                jobprofile.setText("Profile: \t"+JobProfile);
                name.setText(proname);
                phoneno.setText("phone number: \t"+phone_no);
                company.setText("Company Name: \t"+Company);
                jobDescription.setText("Job Description : \n\t\t\t"+job_Decription);
                expDuration.setText("Experience: \t"+expduration+" year");
                eduDec.setText("Education: \t"+edu_dec);
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
mDatabase.addValueEventListener(postListener);
        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.save:
                        startActivity(new Intent(getApplicationContext(),SavedJob.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.appled:
                        startActivity(new Intent(getApplicationContext(),AppliedJob.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),ApplicantDashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void Profile(View view) {
        final DialogPlus dialogPlus = DialogPlus.newDialog(imageButton.getContext())
                .setContentHolder(new ViewHolder(R.layout.profile_experience_item))
                .setExpanded(true,1500)
                .create();
        View view1 = dialogPlus.getHolderView();
        TextInputEditText name = view1.findViewById(R.id.profilename);
        TextInputEditText phoneno = view1.findViewById(R.id.profile_no);
        TextInputEditText jobprofile = view1.findViewById(R.id.editTextTextPersonName2);
        TextInputEditText companyname = view1.findViewById(R.id.editTextTextPersonName3);
        TextInputEditText jobDescription = view1.findViewById(R.id.editTextTextPersonName4);
        TextInputEditText w2w = view1.findViewById(R.id.editTextTextPersonName);
        TextInputEditText edudec = view1.findViewById(R.id.educationdec);
        Button btnUpdate = view1.findViewById(R.id.button);
        name.setText(name.getText().toString());
        phoneno.setText(phoneno.getText().toString());
        jobprofile.setText(jobprofile.getText().toString());
        companyname.setText(companyname.getText().toString());
        jobDescription.setText(jobDescription.getText().toString());
        w2w.setText(w2w.getText());
        edudec.setText(edudec.getText().toString());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String proname = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("fullname").getValue(String.class);
                String phone_no = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("phoneno").getValue(String.class);
                String applicantjobrole = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobrole").getValue(String.class);
                String applicantcompany = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantcompany").getValue(String.class);
                String applicantjobdescription = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobdescription").getValue(String.class);
                String applicantexperience = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantexperience").getValue(String.class);
                String applicanteducationdesc = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicanteducationdesc").getValue(String.class);
                name.setText(proname);
                phoneno.setText(phone_no);
                jobprofile.setText(applicantjobrole);
                companyname.setText(applicantcompany);
                jobDescription.setText(applicantjobdescription);
                w2w.setText(applicantexperience);
                edudec.setText(applicanteducationdesc);
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
                map.put("applicantjobrole",jobprofile.getText().toString());
                map.put("applicantcompany",companyname.getText().toString());
                map.put("applicantjobdescription",jobDescription.getText().toString());
                map.put("applicantexperience",w2w.getText().toString());
                map.put("applicanteducationdesc",edudec.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(jobDescription.getContext(),"data is updated",Toast.LENGTH_LONG).show();
                        dialogPlus.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(jobDescription.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        MenuItem logout = menu.findItem(R.id.lagout);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
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