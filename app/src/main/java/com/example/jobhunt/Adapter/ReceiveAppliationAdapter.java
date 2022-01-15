package com.example.jobhunt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Model.ReceiveApplicationData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ReceiveAppliationAdapter extends FirebaseRecyclerAdapter<ReceiveApplicationData,ReceiveAppliationAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReceiveAppliationAdapter(@NonNull FirebaseRecyclerOptions<ReceiveApplicationData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ReceiveApplicationData model) {
        holder.Company.setText(model.getCompany());
        holder.city.setText(model.getCity());
        holder.jobtitle.setText(model.getTitle());
        holder.des.setText(model.getDescription());
        holder.applicantname.setText(model.getFullname());
        holder.applicantphoneno.setText(model.getPhoneno());
        holder.applicantemail.setText(model.getEmail());
        holder.applicanteducationdesc.setText(model.getApplicanteducationdesc());
        holder.applicantjobrole.setText(model.getApplicantjobrole());
        holder.applicantcompany.setText(model.getApplicantcompany());
        holder.applicantjobdescription.setText(model.getApplicantjobdescription());
        holder.applicantexperience.setText(model.getApplicantexperience());
        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,Object> map = new HashMap<>();
                map.put("status","You Are Selected");

                FirebaseDatabase.getInstance().getReference().child("Applied Status").child(model.getUid()).child(model.getPushid()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(holder.applicantname.getContext(),"Application is Accepted  "+model.getFullname(),Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Toast.makeText(holder.applicantname.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        holder.Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,Object> map = new HashMap<>();
                map.put("status","Application Rejected");

                FirebaseDatabase.getInstance().getReference().child("Applied Status").child(model.getUid()).child(model.getPushid()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(holder.applicantname.getContext(),"Application Rejected  "+model.getFullname(),Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(holder.applicantname.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receive_application_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView Company,city,jobtitle,des,applicantname,applicantphoneno,
                applicantemail,applicanteducationdesc,applicantjobrole,applicantcompany,
                applicantjobdescription,applicantexperience;
        Button Accept,Reject;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Company = itemView.findViewById(R.id.receivecompanyname);
            city = itemView.findViewById(R.id.job_city);
            jobtitle = itemView.findViewById(R.id.job_title);
            des = itemView.findViewById(R.id.descriptiontxt);
            applicantname = itemView.findViewById(R.id.applicantname);
            applicantphoneno = itemView.findViewById(R.id.applicantphoneno);
            applicantemail = itemView.findViewById(R.id.applicantemail);
            applicanteducationdesc = itemView.findViewById(R.id.applicanteducationdesc);
            applicantjobrole = itemView.findViewById(R.id.applicantjobrole);
            applicantcompany = itemView.findViewById(R.id.applicantcompany);
            applicantjobdescription = itemView.findViewById(R.id.applicantjobdescription);
            applicantexperience = itemView.findViewById(R.id.applicantexperience);
            Accept = itemView.findViewById(R.id.acceptjob);
            Reject = itemView.findViewById(R.id.rejectjob);

        }
    }
}
