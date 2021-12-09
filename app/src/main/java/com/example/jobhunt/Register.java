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

public class Register extends AppCompatActivity {
    EditText fname,eid,pw,pwc;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname= findViewById(R.id.fname);
        eid= findViewById(R.id.eid);
        pw= findViewById(R.id.pw);
        pwc = findViewById(R.id.pwc);

        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(Register.this,MainActivity.class));
        }
    }

    public void Direct2(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));

    }
        // Register the user in firebase.
    public void Register(View view) {
        String fullname = fname.getText().toString();
        String emailid = eid.getText().toString().trim();
        String password1 = pw.getText().toString().trim();
        String cpassword = pwc.getText().toString().trim();

        if (TextUtils.isEmpty(fullname)){
            fname.setError("Name Required");
            return;
        }
        if (TextUtils.isEmpty(password1)){
            pw.setError("password required");
            return;
        }
        if (!password1.equals(cpassword)){
            pwc.setError("password does not same");
            return;
        }
        auth.createUserWithEmailAndPassword(emailid,password1).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
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