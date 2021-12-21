package com.example.jobhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {
    EditText emailid;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailid = findViewById(R.id.emailid);
    }
    // Reset the password code
    public void Reset(View view) {
        String email = emailid.getText().toString().trim();
        if(email.isEmpty()){
            emailid.setError("Email id required");
            emailid.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this,"Check the Email reset the password",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ForgetPassword.this,"email id is incorrect",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void Back(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
}