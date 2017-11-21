/*
Name Kiran Koirala
Group #1
 */

package com.example.kiran.inclass09;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextfName;
    private EditText editTextlName;
    private EditText editTextemail;
    private EditText editTextchoosePassword;
    private EditText editTextrepeatPassword;
    private Button buttonsignup;
    private Button buttonCancel;

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = mAuth.getCurrentUser();

            }
        };

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
                String firstName = editTextfName.getText().toString();
                String lastName = editTextlName.getText().toString();
                String email = editTextlName.getText().toString();
                String password = editTextlName.getText().toString().trim();
                String passwordrepeat = editTextlName.getText().toString().trim();


                if (!password.equals(passwordrepeat)){
                    Toast.makeText(SignupActivity.this, "Password don't match", Toast.LENGTH_SHORT).show();
                } else if (firstName.equals("") || lastName.equals("") || email.equals("")
                        || password.equals("")) {
                    Toast.makeText(SignupActivity.this, "One or more field empty", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                        Log.d("demo", "Failed Registration: "+e.getMessage());
                                        //Toast.makeText(SignupActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(SignupActivity.this, "Failed: Check main activity for login infos", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        Intent intent = new Intent(SignupActivity.this, ContactsActivity.class);
                                        startActivity(intent);
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference myRef = database.getReference(user.getUid());

                                        finish();
                                    }
                                }
                            });
                }

            }
        });

    }

}
