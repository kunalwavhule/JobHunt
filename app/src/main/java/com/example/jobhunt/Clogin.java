package com.example.jobhunt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Clogin extends AppCompatActivity {
    EditText email,password;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clogin);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(Clogin.this,PostJobActivity.class));
            finish();
        }

    }

    public void CforgetPassword(View view) {
    }

    public void CDirect(View view) {
    }

    public void CLogin(View view) {
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
                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Clogin.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void RedirectToUserLogin(View view) {
    }

    public void RedirectToAdminLogin(View view) {
    }
}