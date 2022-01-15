package com.example.jobhunt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ApplicantPostJobAdapter extends FirebaseRecyclerAdapter<PostJobData,ApplicantPostJobAdapter.myViewHolder> {
    String name,phone_no,email,applicantcompany,applicantjobrole,applicantjobdescription,applicantexperience,
            applicanteducationdesc;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.     *
     * @param options
     */
    public ApplicantPostJobAdapter(@NonNull FirebaseRecyclerOptions<PostJobData> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PostJobData model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.skill.setText(model.getSkill());
        holder.salary.setText(model.getSalary());
        holder.city.setText(model.getCity());
        holder.schedule.setText(model.getSchedule());
        holder.company.setText(model.getCompany());
        holder.date.setText("posted job :\t"+model.getDate());

       DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
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
        // Unsave the application
        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<>();
                map.put("title",model.getTitle());
                FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(holder.title.getContext(),"job is unsaved "+model.getTitle(),Toast.LENGTH_LONG).show();

                        FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).child(FirebaseAuth.getInstance().getUid()).removeValue();


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
                        Toast.makeText(holder.title.getContext(),"Application is successfully Applied for "+model.getTitle(),Toast.LENGTH_LONG).show();
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
                      //  Toast.makeText(holder.title.getContext(),"Update data on Firebase Save",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_save_job_item,parent,false);
        return new myViewHolder(view);
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView title,description, skill,salary,date,company,city,schedule;
        LinearLayout btnApply,btnSave;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletxt);
            description = itemView.findViewById(R.id.descriptiontxt);
            skill = itemView.findViewById(R.id.skilltxt);
            salary = itemView.findViewById(R.id.salarytxt);
            date = itemView.findViewById(R.id.datetxt);
            schedule = itemView.findViewById(R.id.job_schedule);
            city = itemView.findViewById(R.id.locationtxt);
            company = itemView.findViewById(R.id.companytxt);
            btnApply = itemView.findViewById(R.id.applybtn);
            btnSave = itemView.findViewById(R.id.savebtn);
        }
    }
}
