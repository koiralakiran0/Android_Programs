package com.example.kiran.inclass09;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextfName;
    private EditText editTextlName;
    private EditText editTextemail;
    private EditText editTextchoosePassword;
    private EditText editTextrepeatPassword;
    private Button buttonsignup;
    private Button buttonCancel;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        editTextfName = (EditText) findViewById(R.id.editText_firstname);
        editTextlName = (EditText) findViewById(R.id.editText_lastName);
        editTextemail = (EditText) findViewById(R.id.editText_email);
        editTextchoosePassword = (EditText) findViewById(R.id.editText_password);
        editTextrepeatPassword = (EditText) findViewById(R.id.editText_repeatPass);
        buttonsignup = (Button) findViewById(R.id.button_signup);
        buttonCancel = (Button) findViewById(R.id.button_cancel);

        //Cancel Button
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Signup Button
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextchoosePassword.toString().equals(editTextrepeatPassword.toString())){
                    Toast.makeText(SignupActivity.this, "Password don't match", Toast.LENGTH_SHORT).show();
                }
                else if (validateEmail(editTextemail.toString())){
                    Toast.makeText(SignupActivity.this, "Email not valid", Toast.LENGTH_SHORT).show();
                }else {
                    signupUser();
                }
            }
        });

    }

    //EMAIL VALIDATION
    boolean validateEmail(String email){
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    //User Signup
    //After creating a user, go to the contacts activity
    private void signupUser() {
        String email = editTextemail.getText().toString();
        String password = editTextchoosePassword.getText().toString();


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(SignupActivity.this, "INSIDE HERE", Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "SIGNUP SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignupActivity.this, "UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
