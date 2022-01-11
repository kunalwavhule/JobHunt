package com.example.jobhunt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Applicant.SavedJob;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ApplicantPostJobAdapter extends FirebaseRecyclerAdapter<PostJobData,ApplicantPostJobAdapter.myViewHolder> {
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
        holder.date.setText("posted job :\t"+model.getDate());
        holder.pushid.setText(getRef(position).getKey());
        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<>();

                map.put("title",model.getTitle());
                map.put("description",model.getDescription());
                map.put("skill",model.getSkill());
                map.put("salary",model.getSalary());



                FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(holder.title.getContext(),getRef(position).getKey(),Toast.LENGTH_LONG).show();


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
                map.put(FirebaseAuth.getInstance().getUid(),"2");
                FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(holder.title.getContext(),"data is updated",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(holder.title.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_save_job_item,parent,false);
        return new ApplicantPostJobAdapter.myViewHolder(view);
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView title,description, skill,salary,date,pushid;
        Button btnApply,btnSave;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletxt);
            description = itemView.findViewById(R.id.descriptiontxt);
            skill = itemView.findViewById(R.id.skilltxt);
            salary = itemView.findViewById(R.id.salarytxt);
            date = itemView.findViewById(R.id.datetxt);
            btnApply = itemView.findViewById(R.id.applybtn);
            btnSave = itemView.findViewById(R.id.savebtn);
            pushid = itemView.findViewById(R.id.pushid);
        }
    }
}
