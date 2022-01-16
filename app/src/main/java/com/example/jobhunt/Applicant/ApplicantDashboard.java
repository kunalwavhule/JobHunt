package com.example.jobhunt.Applicant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Login;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.example.jobhunt.myViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ApplicantDashboard extends AppCompatActivity {
    FirebaseAuth auth;
    RecyclerView recyclerView;
    private DatabaseReference mJob,mDatabase;
    String phone_no,name,email,applicanteducationdesc,
            applicantjobrole,applicantcompany,
            applicantjobdescription,applicantexperience;
    FirebaseRecyclerAdapter<PostJobData, myViewHolder> firebaseRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_dashboard);
        getSupportActionBar().setTitle("Applicant Dashboard");
        recyclerView = findViewById(R.id.Aprecview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth = FirebaseAuth.getInstance();
        mJob = FirebaseDatabase.getInstance().getReference().child("Job Post");

        FirebaseRecyclerOptions<PostJobData> options =
                new FirebaseRecyclerOptions.Builder<PostJobData>()
                        .setQuery(mJob, PostJobData.class)
                        .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostJobData, myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PostJobData model) {
                holder.title.setText(model.getTitle());
                holder.description.setText(model.getDescription());
                holder.skill.setText(model.getSkill());
                holder.salary.setText(model.getSalary());
                holder.date.setText("posted job :\t"+model.getDate());
                holder.city.setText(model.getCity());
                holder.jobtypes.setText(model.getJobtypes());
                holder.schedule.setText(model.getSchedule());
                holder.company.setText(model.getCompany());

                mDatabase = FirebaseDatabase.getInstance().getReference();
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("fullname").getValue(String.class);
                        phone_no = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("phoneno").getValue(String.class);
                        email = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("email").getValue(String.class);

                        applicantcompany = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantcompany").getValue(String.class);
                        applicantjobrole = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobrole").getValue(String.class);
                        applicantjobdescription = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobdescription").getValue(String.class);
                        applicantexperience = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantexperience").getValue(String.class);

                        applicanteducationdesc = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicanteducationdesc").getValue(String.class);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                };
                mDatabase.addValueEventListener(postListener);

                //Save Button
                holder.btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put(auth.getUid(),"1");

                        FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.title.getContext(),"Job Saved Suceessfully"+model.getTitle(),Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.title.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                            }
                        });
                                            }
                });
                holder.btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("skill",model.getSkill());
                        map.put("city",model.getCity());
                        map.put("company",model.getCompany());
                        map.put("date",model.getDate());
                        map.put("salary",model.getSalary());
                        map.put("skill",model.getSkill());
                        map.put("title",model.getTitle());
                        map.put("description",model.getDescription());
                        map.put("status","processing");
                        map.put("id",auth.getUid());
                        Map<String,Object> rap = new HashMap<>();
                        rap.put("skill",model.getSkill());
                        rap.put("city",model.getCity());
                        rap.put("company",model.getCompany());
                        rap.put("date",model.getDate());
                        rap.put("salary",model.getSalary());
                        rap.put("skill",model.getSkill());
                        rap.put("title",model.getTitle());
                        rap.put("description",model.getDescription());

                        rap.put("fullname",name);
                        rap.put("phoneno",phone_no);
                        rap.put("email",email);
                        rap.put("applicantjobrole",applicantjobrole);
                        rap.put("applicantcompany", applicantcompany);
                        rap.put("applicantjobdescription",applicantjobdescription);
                        rap.put("applicantexperience",applicantexperience);
                        rap.put("applicanteducationdesc",applicanteducationdesc);
                        rap.put("uid",auth.getUid());
                        rap.put("pushid",getRef(position).getKey());
                        FirebaseDatabase.getInstance().getReference().child("Applied Status").child(auth.getUid()).child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.title.getContext(),"Successfully Applied "+model.getTitle(),Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.title.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                            }
                        });

                        FirebaseDatabase.getInstance().getReference("Applied").child(model.getId()).child(auth.getUid()).updateChildren(rap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.title.getContext(),"Successfully Applied "+model.getTitle(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_post_job_item,parent,false);
                return new myViewHolder(view);

            }
        } ;
        firebaseRecyclerAdapter.startListening();
       recyclerView.setAdapter(firebaseRecyclerAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.save:
                    startActivity(new Intent(getApplicationContext(),SavedJob.class));
                    overridePendingTransition(0,0);
                    return true;
                    case R.id.home:
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
                        .setQuery(mJob.orderByChild("title").startAt(str).endAt(str+"~"), PostJobData.class)
                        .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostJobData, myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PostJobData model) {
                holder.title.setText(model.getTitle());
                holder.description.setText(model.getDescription());
                holder.skill.setText(model.getSkill());
                holder.salary.setText(model.getSalary());
                holder.date.setText("posted job :\t"+model.getDate());
                holder.city.setText(model.getCity());
                holder.jobtypes.setText(model.getJobtypes());
                holder.schedule.setText(model.getSchedule());
                holder.company.setText(model.getCompany());

                mDatabase = FirebaseDatabase.getInstance().getReference();
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("fullname").getValue(String.class);
                        phone_no = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("phoneno").getValue(String.class);
                        email = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("email").getValue(String.class);

                        applicantcompany = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantcompany").getValue(String.class);
                        applicantjobrole = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobrole").getValue(String.class);
                        applicantjobdescription = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantjobdescription").getValue(String.class);
                        applicantexperience = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicantexperience").getValue(String.class);

                        applicanteducationdesc = snapshot.child("User").child(FirebaseAuth.getInstance().getUid()).child("applicanteducationdesc").getValue(String.class);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                };
                mDatabase.addValueEventListener(postListener);

                //Save Button
                holder.btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put(auth.getUid(),"1");

                        FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.title.getContext(),"Job Saved Suceessfully"+model.getTitle(),Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.title.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                holder.btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("skill",model.getSkill());
                        map.put("city",model.getCity());
                        map.put("company",model.getCompany());
                        map.put("date",model.getDate());
                        map.put("salary",model.getSalary());
                        map.put("skill",model.getSkill());
                        map.put("title",model.getTitle());
                        map.put("description",model.getDescription());
                        map.put("status","processing");
                        map.put("id",auth.getUid());
                        Map<String,Object> rap = new HashMap<>();
                        rap.put("skill",model.getSkill());
                        rap.put("city",model.getCity());
                        rap.put("company",model.getCompany());
                        rap.put("date",model.getDate());
                        rap.put("salary",model.getSalary());
                        rap.put("skill",model.getSkill());
                        rap.put("title",model.getTitle());
                        rap.put("description",model.getDescription());

                        rap.put("fullname",name);
                        rap.put("phoneno",phone_no);
                        rap.put("email",email);
                        rap.put("applicantjobrole",applicantjobrole);
                        rap.put("applicantcompany", applicantcompany);
                        rap.put("applicantjobdescription",applicantjobdescription);
                        rap.put("applicantexperience",applicantexperience);
                        rap.put("applicanteducationdesc",applicanteducationdesc);
                        rap.put("uid",auth.getUid());
                        rap.put("pushid",getRef(position).getKey());
                        FirebaseDatabase.getInstance().getReference().child("Applied Status").child(auth.getUid()).child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.title.getContext(),"Successfully Applied "+model.getTitle(),Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.title.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                            }
                        });

                        FirebaseDatabase.getInstance().getReference("Applied").child(model.getId()).child(auth.getUid()).updateChildren(rap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.title.getContext(),"Successfully Applied "+model.getTitle(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_post_job_item,parent,false);
                return new myViewHolder(view);

            }
        } ;
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
  }