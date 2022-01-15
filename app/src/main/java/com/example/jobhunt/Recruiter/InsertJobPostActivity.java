package com.example.jobhunt.Recruiter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jobhunt.Login;
import com.example.jobhunt.Model.PostJobData;
import com.example.jobhunt.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class InsertJobPostActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextInputEditText job_title,job_description,job_skill,job_salary,job_company,job_city,job_qualification,job_jobtypes,job_schedule,job_experience;
    Button btn_post_job;
    //firebase
    FirebaseAuth auth;
    private DatabaseReference mJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);
        getSupportActionBar().setTitle("Post Job");
        auth = FirebaseAuth.getInstance();
        FirebaseUser mUser = auth.getCurrentUser();
        String uid = mUser.getUid();


        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post");

        InsertJob();

    }
    private void InsertJob(){
        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_skill = findViewById(R.id.job_skill);
        job_salary = findViewById(R.id.job_salary);
        job_company = findViewById(R.id.job_company);
        job_city = findViewById(R.id.job_city);
        job_jobtypes = findViewById(R.id.job_jobtypes);
        job_schedule = findViewById(R.id.job_schedule);

        btn_post_job = findViewById(R.id.btn_job_post);
        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = job_title.getText().toString().trim();
                String description = job_description.getText().toString().trim();
                String skill = job_skill.getText().toString().trim();
                String salary = job_salary.getText().toString().trim();
                String company = job_company.getText().toString().trim();
                String city = job_city.getText().toString().trim();
                String jobtypes = job_jobtypes.getText().toString().trim();
                String schedule = job_schedule.getText().toString().trim();


                if (TextUtils.isEmpty(title)){
                    job_title.setError("Required Field....");
                    return;
                }
                if (TextUtils.isEmpty(description)){
                    job_description.setError("Required Field....");
                    return;
                }
                if (TextUtils.isEmpty(skill)){
                    job_skill.setError("Required Field....");
                    return;
                }
                if (TextUtils.isEmpty(salary)){
                    job_salary.setError("Required Field....");
                    return;
                }
                if (TextUtils.isEmpty(company)){
                    job_company.setError("Required Field....");
                    return;
                }
                if (TextUtils.isEmpty(city)){
                    job_city.setError("Required Field....");
                    return;
                }


                if (TextUtils.isEmpty(jobtypes)){
                    job_jobtypes.setError("Required Field....");
                    return;
                }

                if (TextUtils.isEmpty(schedule)){
                    job_schedule.setError("Required Field....");
                    return;
                }


                String id = mJobPost.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());
               PostJobData postJobData = new PostJobData(title,description,skill,salary, auth.getUid(), date,id,company,city,"Processing",jobtypes,schedule);

                mJobPost.child(id).setValue(postJobData);
                Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), RecruiterDashboard.class));
                finish();


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        MenuItem logout = menu.findItem(R.id.lagout);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
