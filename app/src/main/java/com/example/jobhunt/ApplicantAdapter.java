package com.example.jobhunt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobhunt.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ApplicantAdapter extends FirebaseRecyclerAdapter<Data,ApplicantAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ApplicantAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Data model) {
        holder.id.setText(model.getId());
        holder.fullname.setText(model.getFullname());
        holder.email.setText(model.getEmail());
        holder.phoneno.setText(model.getPhoneno());
        holder.date.setText(model.getDate());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_item,parent,false);
        return new ApplicantAdapter.myViewHolder(view);

    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView id, fullname, email,phoneno ,date;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idap);
            fullname = itemView.findViewById(R.id.fullnameap);
            email = itemView.findViewById(R.id.emailap);
            phoneno = itemView.findViewById(R.id.phonenoap);
            date = itemView.findViewById(R.id.dateap);        }
    }
}
