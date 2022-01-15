package com.example.jobhunt.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class PostJobAdapter extends FirebaseRecyclerAdapter<PostJobData, PostJobAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PostJobAdapter(@NonNull FirebaseRecyclerOptions<PostJobData> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PostJobData model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.skill.setText(model.getSkill());
        holder.salary.setText(model.getSalary());
        holder.date.setText(model.getDate());
        holder.company.setText(model.getCompany());
        holder.city.setText(model.getCity());
        holder.jobtypes.setText(model.getJobtypes());
        holder.schedule.setText(model.getSchedule());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.btnEdit.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updateform))
                        .setExpanded(true,1500)
                        .create();
                View view1 = dialogPlus.getHolderView();
                TextInputEditText title = view1.findViewById(R.id.titleupdate);
                TextInputEditText des = view1.findViewById(R.id.desupdate);
                TextInputEditText skill = view1.findViewById(R.id.skillupdate);
                TextInputEditText salary = view1.findViewById(R.id.salaryupdate);
                TextInputEditText companyname = view1.findViewById(R.id.companyupdate);
                TextInputEditText jobtypes = view1.findViewById(R.id.jobtypesupdate);
                TextInputEditText schedule = view1.findViewById(R.id.scheduleupdate);
                TextInputEditText city = view1.findViewById(R.id.cityupdate);

                Button btnUpdate = view1.findViewById(R.id.updatebtn);
                title.setText(model.getTitle());
                des.setText(model.getDescription());
                skill.setText(model.getSkill());
                salary.setText(model.getSalary());
                companyname.setText(model.getCompany());
                jobtypes.setText(model.getJobtypes());
                schedule.setText(model.getSchedule());
                city.setText(model.getCity());
                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("title",title.getText().toString());
                        map.put("description",des.getText().toString());
                        map.put("skill",skill.getText().toString());
                        map.put("salary",salary.getText().toString());
                        map.put("company",companyname.getText().toString());
                        map.put("jobtypes",jobtypes.getText().toString());
                        map.put("schedule",schedule.getText().toString());
                        map.put("city",city.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.title.getContext(),"data is updated",Toast.LENGTH_LONG).show();
                                dialogPlus.dismiss();
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
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Deleted data can't be undo");
                builder.setMessage("deleted data can't be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Job Post").child(getRef(position).getKey()).removeValue();  }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.title.getContext(),"cancelled",Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_job_item,parent,false);
        return new myViewHolder(view);
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView title,description, skill,salary,date,company,city,jobtypes,schedule;
        LinearLayout btnDelete,btnEdit;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletxt);
            description = itemView.findViewById(R.id.descriptiontxt);
            skill = itemView.findViewById(R.id.skilltxt);
            salary = itemView.findViewById(R.id.salarytxt);
            date = itemView.findViewById(R.id.datetxt);
            company = itemView.findViewById(R.id.companytxt);
            city = itemView.findViewById(R.id.locationtxt);
            jobtypes = itemView.findViewById(R.id.job_jobtypes);
            schedule = itemView.findViewById(R.id.job_schedule);
            btnEdit = itemView.findViewById(R.id.editbtn);
            btnDelete = itemView.findViewById(R.id.deletebtn);
        }
    }
}
