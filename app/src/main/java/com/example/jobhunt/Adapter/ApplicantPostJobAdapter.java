package com.example.jobhunt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ApplicantPostJobAdapter extends FirebaseRecyclerAdapter<PostJobData,ApplicantPostJobAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
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
        holder.date.setText(model.getDate());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_post_job_item,parent,false);
        return new ApplicantPostJobAdapter.myViewHolder(view);

    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView title,description, skill,salary,date;
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


        }
    }
}
