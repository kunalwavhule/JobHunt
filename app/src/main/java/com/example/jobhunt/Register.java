package com.example.jobhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobhunt.Model.ApplicantData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Register extends AppCompatActivity {
    EditText aprname,apremailid,aprpw,aprcpw,aprgender,aprphoneno;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       aprname = findViewById(R.id.aprname);
       apremailid = findViewById(R.id.apremailid);
       aprpw = findViewById(R.id.aprpassword);
       aprcpw = findViewById(R.id.aprcpw);
       aprgender = findViewById(R.id.aprgender);
       aprphoneno = findViewById(R.id.aprphoneno);

    }

    public void Back(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void RedirectToApplicant(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void ApplicantRegister(View view) {
        String apname = aprname.getText().toString();
        String apemailid = apremailid.getText().toString().trim();
        String appassword = aprpw.getText().toString().trim();
        String apcpassword = aprcpw.getText().toString().trim();
        String apgender = aprgender.getText().toString().trim();
        String apphoneno = aprphoneno.getText().toString().trim();


        if (TextUtils.isEmpty(apname)){
            aprname.setError("Name Required");
            return;
        }
        if (TextUtils.isEmpty(appassword)){
            aprpw.setError("password required");
            return;
        }
        if (!appassword.equals(apcpassword)){
            aprcpw.setError("password does not same");
            return;
        }
        if (TextUtils.isEmpty(apgender)){
            aprgender.setError("password required");
            return;
        }
        if (TextUtils.isEmpty(apphoneno)){
            aprphoneno.setError("password required");
            return;
        }


        auth.createUserWithEmailAndPassword(apemailid,appassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                //save data in firebase database
                String date = DateFormat.getDateInstance().format(new Date());
                String id = authResult.getUser().getUid();

                ApplicantData applicantData = new ApplicantData(id,apname,apemailid,appassword,apgender,apphoneno,date,0);
                firebaseDatabase.getReference().child("Applicant").child(id).setValue(applicantData);

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
}