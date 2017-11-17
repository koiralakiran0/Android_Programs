package com.example.kiran.inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private ListView container;
    private Button button_createNew;
    private Button button_logout;
    //ArrayList<Contact> defaultvalues = new ArrayList<>();

    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(user.getUid());

        /*
        defaultvalues.add(new Contact("Kiran Koirala", "email@whatever.com", "asdfas", "asdfasdf", "fasdfasdf"));
        defaultvalues.add(new Contact("kron vron", "email@whatever.com", "asdfas", "asdfasdf", "fasdfasdf"));
        defaultvalues.add(new Contact("sron Koirala", "email@whatever.com", "asdfas", "asdfasdf", "fasdfasdf"));
        defaultvalues.add(new Contact("don Koirala", "email@whatever.com", "asdfas", "asdfasdf", "fasdfasdf"));
        defaultvalues.add(new Contact("flon vlon", "email@whatever.com", "asdfas", "asdfasdf", "fasdfasdf"));
        */

        button_createNew = (Button) findViewById(R.id.button_createNewContact);
        button_logout = (Button) findViewById(R.id.button_Logout);
        container = (ListView) findViewById(R.id.listview_container);

        fillContainer();

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
                mAuth.signOut();
                finish();
            }
        });
    }

    private void fillContainer() {
        //Fill the Listview

    }
}
