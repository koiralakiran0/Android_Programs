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
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = mAuth.getCurrentUser();

            }
        };


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
                    loginUsers();
                }
            });

        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("class").push().setValue("object");
        */
    }

    private void loginUsers() {
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        Log.d("demo", "I am here");

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d("demo", "Im successful");
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(MainActivity.this, "Authentication Passed.", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    // If sign in fails, display a message to the user.
                    //Log.d("demo", "signInWithCustomToken:failure");
                    //Log.d("demo", "Im failure");
                    //Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
        });

    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

}
