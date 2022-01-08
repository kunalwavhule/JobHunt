package com.example.jobhunt;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.CollationElementIterator;

public class myViewHolder extends RecyclerView.ViewHolder{
    public TextView title;
    public TextView description;
    public TextView skill;
    public TextView salary;
    public TextView date;
    public TextView pushid;
    public Button btnApply;
    public Button btnSave;
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