package com.example.jobhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,password;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }
    public void Login(View view){
        String emailid = email.getText().toString().trim();
        String passw = password.getText().toString().trim();













        
    }
}