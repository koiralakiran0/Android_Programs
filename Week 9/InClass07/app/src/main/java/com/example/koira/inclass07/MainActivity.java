package com.example.koira.inclass07;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacts> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment contact = (Fragment) new Contacts();
        getFragmentManager().beginTransaction()
                .add(R.id.Container, contact , "contact_tag")
                .commit();
    }
}
