package com.example.potholedetectionapp;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivitySignIn extends AppCompatActivity {

    private TextView textDOB;
    private Button buttonDOB;
    private Calendar dobCalendar;
    private EditText editTextName, editTextPhoneNumber, editTextEmail, editTextUsername, editTextPassword;
    private Button buttonSignIn,buttonRegLogin;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_in);

        // Initialize views
        textDOB = findViewById(R.id.text_dob);
        buttonDOB = findViewById(R.id.button_dob);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonRegister);
        buttonRegLogin =findViewById(R.id.buttonRegLogin);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String email = editTextEmail.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                mAuth=FirebaseAuth.getInstance();
                mUser=mAuth.getCurrentUser();
                //for DateOfBirth
                dobCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener dobDatePickerListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dobCalendar.set(Calendar.YEAR, year);
                        dobCalendar.set(Calendar.MONTH, monthOfYear);
                        dobCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        updateDOBText();
                    }
                };

                buttonDOB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(MainActivitySignIn.this, dobDatePickerListener,
                                dobCalendar.get(Calendar.YEAR),
                                dobCalendar.get(Calendar.MONTH),
                                dobCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                // Perform format checks

                if (TextUtils.isEmpty(name)) {
                    editTextName.setError("Please enter your name");
                    editTextName.requestFocus();
                    return;
                }

                else if (!isValidPhoneNumber(phoneNumber)) {
                    editTextPhoneNumber.setError("Please enter a valid phone number");
                    editTextPhoneNumber.requestFocus();
                    return;
                }

                else if (!isEmailValid(email)) {
                    editTextEmail.setError("Please enter a valid email address");
                    editTextEmail.requestFocus();
                    return;
                }

                else if (!isValidUsername(username)) {
                    editTextUsername.setError("Please enter a valid username");
                    editTextUsername.requestFocus();
                    return;
                }

                else if (!isValidPassword(password)) {
                    editTextPassword.setError("Please enter a valid password");
                    editTextPassword.requestFocus();
                    return;
                }
                else {
                    progressD.setMessage("please wait a while..");
                    progressD.setTitle("Regestration");
                    progressD.setCanceledOnTouchOutside(false);
                    progressD.show();
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressD.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(MainActivitySignIn.this,"Successful",Toast.LENGTH_SHORT);
                            }
                            else
                            {
                                progressD.dismiss();
                                Toast.makeText(MainActivitySignIn.this,"Error",Toast.LENGTH_SHORT);
                            }

                        }
                    });

                }


                // If all format checks pass, you can proceed with sign-in logic
                // For simplicity, let's just display a toast message

                String message = "Name: " + name + "\n" +
                        "Phone Number: " + phoneNumber + "\n" +
                        "Email: " + email + "\n" +
                        "Username: " + username + "\n" +
                        "Password: " + password;

                Toast.makeText(MainActivitySignIn.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        buttonRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the signup activity
                Intent intent = new Intent(MainActivitySignIn.this, MainActivityLogIn.class);
                startActivity(intent);
            }
        });

    }

    private void updateDOBText() {
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivitySignIn.this,MainActivityHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // Helper methods for format checks

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Perform your phone number format validation here
        // For example, check if the phone number has 10 digits
        return phoneNumber.matches("\\d{10}");
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidUsername(String username) {
        // Perform your username format validation here
        // You can define your own rules, such as minimum length, allowed characters, etc.
        return username.length() >= 4;
    }

    private boolean isValidPassword(String password) {
        // Perform your password format validation here
        // You can define your own rules, such as minimum length, required characters, etc.
        return password.length() >= 6;
    }
}
