package com.example.jobhunt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jobhunt.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class InsertJobPostActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText job_title,job_description,job_skill,job_salary;
    Button btn_post_job;
    //firebase
    FirebaseAuth auth;
    private DatabaseReference mJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);
        toolbar = findViewById(R.id.incert_job_toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Post Job");
        auth = FirebaseAuth.getInstance();
        FirebaseUser mUser = auth.getCurrentUser();
        String uid = mUser.getUid();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uid);

        InsertJob();

    }
    private void InsertJob(){
        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_skill = findViewById(R.id.job_skill);
        job_salary = findViewById(R.id.job_salary);

        btn_post_job = findViewById(R.id.btn_job_post);
        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = job_title.getText().toString().trim();
                String description = job_description.getText().toString().trim();
                String skill = job_skill.getText().toString().trim();
                String salary = job_salary.getText().toString().trim();

                if (TextUtils.isEmpty(title)){
                    job_title.setError("Required Feild....");
                    return;
                }
                if (TextUtils.isEmpty(description)){
                    job_description.setError("Required Feild....");
                    return;
                }
                if (TextUtils.isEmpty(skill)){
                    job_skill.setError("Required Feild....");
                    return;
                }
                if (TextUtils.isEmpty(salary)){
                    job_salary.setError("Required Feild....");
                    return;
                }

                String id = mJobPost.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());
                Data data = new Data(title,description,skill,salary,id,date);

                mJobPost.child(id).setValue(data);
                Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));


            }
        });
    }
}