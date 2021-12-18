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

public class AdminLogin extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    EditText AdminUsername,AdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        AdminUsername = findViewById(R.id.Adminusername);
        AdminPassword = findViewById(R.id.Adminpassword);
    }


    public void RedirectToUserLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void Back(View view) {
        startActivity(new Intent(getApplicationContext(),UserTypes.class));
        finish();
    }

    public void AdminLogin(View view) {

        String emailid = AdminUsername.getText().toString().trim();
        String passw = AdminPassword.getText().toString().trim();

        if (TextUtils.isEmpty(emailid)){
            AdminUsername.setError("email id is required");
            return;
        }
        if (TextUtils.isEmpty(passw)){
            AdminPassword.setError("password is empty");
            return;
        }
        if (passw.length()<8){
            AdminPassword.setError("password length must be 8 char long");
            return;
        }
        auth.signInWithEmailAndPassword(emailid,passw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(getApplicationContext(),AdminDashBoard.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminLogin.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}