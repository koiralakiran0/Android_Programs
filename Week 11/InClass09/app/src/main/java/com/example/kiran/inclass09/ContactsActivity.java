package com.example.kiran.inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactsActivity extends AppCompatActivity {


    private Button button_createNew;
    private Button button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        button_createNew = (Button) findViewById(R.id.button_createNewContact);
        button_logout = (Button) findViewById(R.id.button_Logout);

        button_createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goto createNewContact
                Intent intent = new Intent(ContactsActivity.this, CreateNewContact.class);
                startActivity(intent);
            }
        });

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete authentication
                //go to login screen
                finish();
            }
        });
    }
}
