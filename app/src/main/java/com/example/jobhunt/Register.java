package com.example.jobhunt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobhunt.Model.Data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Register extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    EditText name,email,password,cpassword,phoneno;
    CheckBox applicant,recruiter;
    int types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        phoneno = findViewById(R.id.phoneno);
        recruiter = findViewById(R.id.recruiter);
        applicant = findViewById(R.id.applicant);

        recruiter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    applicant.setChecked(false);
                    types = 1;
                }
            }
        });

        applicant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    recruiter.setChecked(false);
                    types = 0;
                }
            }
        });

    }

    public void Register(View view) {

        String rname = name.getText().toString();
        String remail = email.getText().toString().trim();
        String rpassword = password.getText().toString().trim();
        String rcpassword = cpassword.getText().toString().trim();
        //String apgender = aprgender.getText().toString().trim();
        String rphoneno = phoneno.getText().toString().trim();

        if (TextUtils.isEmpty(rname)){
            name.setError("Name Required");
            return;
        }
        if (TextUtils.isEmpty(rpassword)){
            password.setError("password required");
            return;
        }
        if (!rpassword.equals(rcpassword)){
            cpassword.setError("password does not same");
            return;
        }
        if (TextUtils.isEmpty(rphoneno)){
            phoneno.setError("password required");
            return;
        }
        auth.createUserWithEmailAndPassword(remail,rpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String date = DateFormat.getDateInstance().format(new Date());
                String id = authResult.getUser().getUid();

                Data data = new Data(id,rname,remail,rpassword,rphoneno,date,types);


               firebaseDatabase.getReference().child("User").child(id).setValue(data);
               Toast.makeText(Register.this,"Register successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Login.class));



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }

    public void Login(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}