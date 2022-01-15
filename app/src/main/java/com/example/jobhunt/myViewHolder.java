package com.example.jobhunt;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder{
    public TextView title;
    public TextView description;
    public TextView skill;
    public TextView salary;
    public TextView date,city,schedule,jobtypes,company;
    public LinearLayout btnApply,btnSave;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titletxt);
        description = itemView.findViewById(R.id.descriptiontxt);
        skill = itemView.findViewById(R.id.skilltxt);
        salary = itemView.findViewById(R.id.salarytxt);
        date = itemView.findViewById(R.id.datetxt);
        city = itemView.findViewById(R.id.locationtxt);
        schedule = itemView.findViewById(R.id.job_schedule);
        jobtypes = itemView.findViewById(R.id.job_jobtypes);
        company = itemView.findViewById(R.id.companytxt);
        btnApply = itemView.findViewById(R.id.applybtn);
        btnSave = itemView.findViewById(R.id.savebtn);
    }
}