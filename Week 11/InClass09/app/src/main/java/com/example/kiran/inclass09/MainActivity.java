package com.example.kiran.inclass09;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private EditText editText_email;
    private EditText editText_password;
    private Button button_login;
    private Button button_signup;

    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

            editText_email = (EditText) findViewById(R.id.editText_email);
            editText_password = (EditText) findViewById(R.id.editText_password);
            button_login = (Button) findViewById(R.id.button_login);
            button_signup = (Button) findViewById(R.id.button_signup);

            button_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                    startActivity(intent);
                }
            });

            button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //loginUsers();
                    Intent intent = new Intent(MainActivity.this, CreateNewContact.class);
                    startActivity(intent);
                }
            });

        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("class").push().setValue("object");
        */
    }



    //After loggin in, goto the contacts activity
    private void loginUsers() {
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        Log.d("demo", "I am here");

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(MainActivity.this, "Task Successful", Toast.LENGTH_SHORT).show();

                if (!task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "NOT SUCCESSFUL", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });


    }

}
