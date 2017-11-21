
/*
Name Kiran Koirala
Group #1
 */
package com.example.kiran.inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {


    private Button button_createNew;
    private Button button_logout;
    private ListView container;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    ArrayList<Contact> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(user.getUid()).child("Contacts");

        contacts = new ArrayList<>();

        button_createNew = (Button) findViewById(R.id.button_createNewContact);
        button_logout = (Button) findViewById(R.id.button_Logout);
        container = (ListView) findViewById(R.id.listview_container);

        //When Create New is started
        button_createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goto createNewContact
                Intent intent = new Intent(ContactsActivity.this, CreateNewContact.class);
                startActivity(intent);

                finish();
            }
        });

        //When Logout is clicked
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete authentication
                mAuth.signOut();
                //go to login screen
                finish();
            }
        });

        //When data is changed
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = "" + dataSnapshot.child("name").getValue();
                String email = "" + dataSnapshot.child("email").getValue();
                String phone = "" + dataSnapshot.child("phone").getValue();
                String department = "" + dataSnapshot.child("department").getValue();
                String image = "" + dataSnapshot.child("image").getValue();

                Contact contact = new Contact(name, email, phone, department, image);
                contacts.add(contact);
                generateView();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void generateView() {
        ContactsAdapter adapter = new ContactsAdapter(ContactsActivity.this, R.layout.contact_item, contacts);
        container.setAdapter(adapter);

        container.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String key = contacts.get(i).uid;
                myRef.child(key).removeValue();

                contacts.remove(i);
                return false;
            }
        });
    }
}
