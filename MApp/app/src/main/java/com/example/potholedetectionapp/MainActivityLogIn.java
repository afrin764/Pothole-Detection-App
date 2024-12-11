package com.example.potholedetectionapp;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityLogIn extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonSignup;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log_in);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonlog);
        buttonSignup = findViewById(R.id.buttonLogReg);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!isValidEmail(email)) {
                    editTextEmail.setError("Please enter a valid email address");
                    editTextEmail.requestFocus();
                    return;
                }

                if (!isValidPassword(password)) {
                    editTextPassword.setError("Please enter a valid password");
                    editTextPassword.requestFocus();
                    return;
                }

                // Perform login logic here
                // ...

                // Example: Navigate to another activity
                Intent intent = new Intent(MainActivityLogIn.this, MainActivityHome.class);
                startActivity(intent);
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the signup activity
                Intent intent = new Intent(MainActivityLogIn.this, MainActivitySignIn.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidEmail(String email) {
        this.email = email;
        // Perform email format validation here
        // You can use a regular expression pattern or a library like Patterns.EMAIL_ADDRESS
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        this.password = password;
        // Perform password validation here
        // You can define your own rules, such as minimum length, required characters, etc.
        return password.length() >= 6;
    }
}

