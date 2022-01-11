package com.example.jobhunt.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Model.Data;
import com.example.jobhunt.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UserAdapter extends FirebaseRecyclerAdapter<Data, UserAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Data model) {
        holder.fullname.setText(model.getFullname());
        holder.email.setText(model.getEmail());
        holder.phoneno.setText(model.getPhoneno());
        holder.date.setText(model.getDate());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.fullname.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Deleted data cann't be undo");
                builder.setMessage("deleted data cann't be undo");
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_item,parent,false);
        return new UserAdapter.myViewHolder(view);
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView  fullname, email,phoneno ,date;
        Button delete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.fullnameap);
            email = itemView.findViewById(R.id.emailap);
            phoneno = itemView.findViewById(R.id.phonenoap);
            date = itemView.findViewById(R.id.dateap);
            delete = itemView.findViewById(R.id.deletebtn);
        }
    }
}
