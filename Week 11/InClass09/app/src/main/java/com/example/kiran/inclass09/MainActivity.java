/*
Name Kiran Koirala
Group #1
 */

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private EditText editText_email;
    private EditText editText_password;
    private Button button_login;
    private Button button_signup;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    /*
    LOGIN USERS IN CASE SIGNUP DOESN't work
    email: loginuncc@gmail.com  pass: reponse
    email: yesno@gmail.com  pass:yesnot
    email: asdfasdf@gmail.com   pass: asfdasdfa
    email: abcdasdfefgh@gmail.com   pass: werwre
    email: asdfasdf@gmail.com   pass: sfasdffc
    email: jkljkljkljkefgh@gmail.com   pass: wertghj
    email: werqwerrtghju@gmail.com   pass: eyertet
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("Test", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Test", "Failed to read value.", error.toException());
            }
        });


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
                    if (!editText_email.getText().toString().equals("") && !editText_password.getText().toString().equals("")) {
                        loginUsers();
                    }else {
                        Toast.makeText(MainActivity.this, "Enter email and password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void loginUsers() {
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        Log.d("demo", "I am here");

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("demo", "I am successful");
                    Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                    startActivity(intent);
                    editText_email.setText("");
                    editText_password.setText("");
                }else {
                    Toast.makeText(MainActivity.this, "LOG IN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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
