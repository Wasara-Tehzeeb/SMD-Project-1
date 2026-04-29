package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText etSignupEmail, etSignupPassword, etConfirmPassword;
        Button btnSignup;

        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etSignupEmail.getText().toString().trim();
                String password = etSignupPassword.getText().toString();
                String confirmPass = etConfirmPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length()<8){
                    Toast.makeText(SignupActivity.this, "Password must be atleast 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmPass)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult){
                            Toast.makeText(SignupActivity.this,"User created",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}