package com.example.kiran.inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextfName;
    private EditText editTextlName;
    private EditText editTextemail;
    private EditText editTextchoosePassword;
    private EditText editTextrepeatPassword;
    private Button buttonsignup;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
                Intent intent = new Intent(SignupActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

    }
}
