package com.example.jobhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobhunt.Model.RecruiterData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class RecruiterRegister extends AppCompatActivity {

    EditText rname,remailid,rpassword,rcpassword,rgender,rphoneno;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_register);
        rname = findViewById(R.id.rname);
        remailid = findViewById(R.id.remailid);
        rpassword = findViewById(R.id.rpassword);
        rcpassword = findViewById(R.id.rpwc);
        rgender = findViewById(R.id.rgender);
        rphoneno = findViewById(R.id.rphoneno);
    }

    public void RecruiterRegister(View view) {
        String recruitername = rname.getText().toString();
        String recruiteremailid = remailid.getText().toString().trim();
        String recruiterpassword = rpassword.getText().toString().trim();
        String recruitercpassword = rcpassword.getText().toString().trim();
        String recruitergender = rgender.getText().toString().trim();
        String recruiterphoneno = rphoneno.getText().toString().trim();

        if (TextUtils.isEmpty(recruitername)){
            rname.setError("Name Required");
            return;
        }
        if (TextUtils.isEmpty(recruiterpassword)){
            rpassword.setError("password required");
            return;
        }
        if (!recruiterpassword.equals(recruitercpassword)){
            rcpassword.setError("password does not same");
            return;
        }
        if (TextUtils.isEmpty(recruiteremailid)){
            remailid.setError("Email Required");
            return;
        }
        if (TextUtils.isEmpty(recruitergender)){
            rgender.setError("Gender Required");
            return;
        }
        if (TextUtils.isEmpty(recruiterphoneno)){
            rphoneno.setError("phone number Required");
            return;
        }
        auth.createUserWithEmailAndPassword(recruiteremailid,recruiterpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                //save data in firebase database
                String date = DateFormat.getDateInstance().format(new Date());
                String id = authResult.getUser().getUid();

                RecruiterData recruiterData = new RecruiterData(id,recruitername,recruiteremailid,recruiterpassword,recruitergender,recruiterphoneno,date,1);

                firebaseDatabase.getReference().child("Recruiter").child(id).setValue(recruiterData);

                Toast.makeText(RecruiterRegister.this,"Register successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),RecruiterLogin.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RecruiterRegister.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    public void RedirectToRecruitersLogin(View view) {
        startActivity(new Intent(getApplicationContext(),RecruiterLogin.class));
        finish();
    }

    public void Back(View view) {
        startActivity(new Intent(getApplicationContext(),RecruiterLogin.class));
        finish();
    }
}