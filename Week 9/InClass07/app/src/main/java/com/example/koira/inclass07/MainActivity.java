package com.example.koira.inclass07;

/*
Assignment # : In Class 7
File Name: MainActivity.java
Full Names: Kiran Koirala
 */
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Contacts.OnFragmentInteractionListener{

    ArrayList<Contact> manyContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manyContacts.add(new Contact("Kiran", "kkoirala@uncc.edu", "9804222868", "CS", ""));
        manyContacts.add(new Contact("asdf", "asdf@uncc.asdf", "asdfasdf", "CS", ""));
        manyContacts.add(new Contact("asdfasdfasdf", "asdf@asdfas.edu", "ssdfdsfsaf", "CS", ""));

        getFragmentManager().beginTransaction()
                .add(R.id.Container, new Contacts(), "contact_tag")
                .commit();
    }

    @Override
    public void onBackPressed()
    {
        if (getFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void gotoCreateNew() {
        getFragmentManager().beginTransaction()
                .replace(R.id.Container, new CreateNewContact(), "create_new")
                .addToBackStack(null)
                .commit();
    }

}
