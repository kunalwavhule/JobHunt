package com.example.jobhunt.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Model.Data;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RecruiterAdapter extends FirebaseRecyclerAdapter<Data,RecruiterAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecruiterAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Data model) {
        holder.fullname.setText(model.getFullname());
        holder.email.setText(model.getEmail());
        holder.phoneno.setText(model.getPhoneno());
        holder.date.setText("Created Account  "+model.getDate());
        holder.companyaddress.setText(model.getCompanyaddress());
        holder.companytypes.setText("Industry : "+model.getCompanytypes());
        holder.companydesc.setText("Description : "+model.getCompanydesc());
        holder.companyopeninghours.setText("Opening time : "+model.getCompanyopeninghours());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.fullname.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Deleted data can't be undo");
                builder.setMessage("deleted data can't be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("User").child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.fullname.getContext(),"cancelled",Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recruiter_item,parent,false);
        return new RecruiterAdapter.myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView fullname, email,phoneno ,date, delete,companyaddress,companytypes,companydesc,companyopeninghours;
        // Button delete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.fullnameap);
            email = itemView.findViewById(R.id.emailap);
            phoneno = itemView.findViewById(R.id.phonenoap);
            date = itemView.findViewById(R.id.dateap);
            delete = itemView.findViewById(R.id.deletebtn);
            companyaddress = itemView.findViewById(R.id.companyaddress);
            companytypes = itemView.findViewById(R.id.companytypes);
            companydesc = itemView.findViewById(R.id.companydesc);
            companyopeninghours = itemView.findViewById(R.id.companyopeninghours);

        }
    }
}
