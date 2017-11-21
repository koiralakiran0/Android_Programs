package com.example.kiran.inclass10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;

    private FirebaseStorage storage; //Cloud firebase
    private StorageReference storageRef; //storage reference from the app
    private StorageReference imageRef; //child reference to the storageRef
    private StorageReference spaceRef; //child reference with path

    /*
    Methods

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        imageRef = storageRef.child("images");
        spaceRef = storageRef.child("images/space.jpg");
    }
}
