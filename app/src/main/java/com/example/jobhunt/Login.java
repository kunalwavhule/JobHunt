package com.example.jobhunt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobhunt.Admin.AdminDashBoard;
import com.example.jobhunt.Applicant.ApplicantDashboard;
import com.example.jobhunt.Recruiter.RecruiterDashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText email,password;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);

        if (auth.getCurrentUser()!=null){

            FirebaseUser mUser = auth.getCurrentUser();
            String uid = mUser.getUid();
            firebaseDatabase.getReference().child("User").child(uid).child("userTypes").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int userTypes = snapshot.getValue(Integer.class);
                    if (userTypes == 0){
                        Intent in = new Intent(Login.this,ApplicantDashboard.class);
                        startActivity(in);
                    }
                    if (userTypes == 1){
                        Intent in = new Intent(Login.this,RecruiterDashboard.class);
                        startActivity(in);
                    }
                    if (userTypes == 2){
                        Intent in = new Intent(Login.this,AdminDashBoard.class);
                        startActivity(in);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }

    // Login the firebase code
    public void Login(View view) {
        String emailid = email.getText().toString().trim();
        String passw = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailid)){
            email.setError("email id is required");
            return;
        }
        if (TextUtils.isEmpty(passw)){
            password.setError("password is empty");
            return;
        }
        if (passw.length()<8){
            password.setError("password length must be 8 char long");
            return;
        }
        auth.signInWithEmailAndPassword(emailid,passw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                String uid = authResult.getUser().getUid();
                firebaseDatabase.getReference().child("User").child(uid).child("userTypes").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int userTypes = snapshot.getValue(Integer.class);
                        if (userTypes == 0){
                            Intent in = new Intent(Login.this, ApplicantDashboard.class);
                            startActivity(in);
                            finish();
                        }
                        if (userTypes == 1){
                            Intent in = new Intent(Login.this, RecruiterDashboard.class);
                            startActivity(in);
                            finish();
                        }
                        if (userTypes == 2){
                            Intent in = new Intent(Login.this, AdminDashBoard.class);
                            startActivity(in);
                            finish();
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
                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    // Redirect to Register Activity
    public void Direct(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));

    }


    public void ForgetPassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
    }
}