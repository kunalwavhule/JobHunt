package com.example.jobhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecruiterLogin extends AppCompatActivity {
    EditText remail,rpassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_login);
        remail = findViewById(R.id.recruiterusername);
        rpassword = findViewById(R.id.recruiterusername);

        if (auth.getCurrentUser()!=null) {

            FirebaseUser mUser = auth.getCurrentUser();
            String uid = mUser.getUid();
            firebaseDatabase.getReference().child("Recruiter").child(uid).child("userTypes").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int userTypes = snapshot.getValue(Integer.class);
                    if (userTypes == 1) {
                        Intent in = new Intent(RecruiterLogin.this, PostJobActivity.class);
                        startActivity(in);
                    }
                    if (userTypes == 0) {
                        Toast.makeText(getApplicationContext(), "invalid user", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    public void RecruiterLogin(View view) {
        String emailid = remail.getText().toString().trim();
        String passw = rpassword.getText().toString().trim();

        if (TextUtils.isEmpty(emailid)){
            remail.setError("email id is required");
            return;
        }
        if (TextUtils.isEmpty(passw)){
            rpassword.setError("password is empty");
            return;
        }

        auth.signInWithEmailAndPassword(emailid,passw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                String uid = authResult.getUser().getUid();
                firebaseDatabase.getReference().child("Recruiter").child(uid).child("userTypes").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int userTypes = snapshot.getValue(Integer.class);
                        if (userTypes == 1){
                            Intent in = new Intent(RecruiterLogin.this,PostJobActivity.class);
                            startActivity(in);
                        }
                        if (userTypes == 0){
                            Toast.makeText(getApplicationContext(),"invalid user",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RecruiterLogin.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

    public void RecruitersForgetPassword(View view) {

        startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
    }

    public void RedirectToRecruitersRegistration(View view) {
        startActivity(new Intent(getApplicationContext(),RecruiterRegister.class));
        finish();

    }


    public void Back(View view) {
        startActivity(new Intent(getApplicationContext(),UserTypes.class));
        finish();
    }
}