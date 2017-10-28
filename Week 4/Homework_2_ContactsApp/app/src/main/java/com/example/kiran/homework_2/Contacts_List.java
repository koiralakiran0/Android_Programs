package com.example.kiran.homework_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Contacts_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts__list);

        ArrayList<Contacts> contactses =  new ArrayList<>();
        contactses.add(new Contacts("user", "1" , "1234567890"));
        contactses.add(new Contacts("user", "2" , "1234567890"));
        contactses.add(new Contacts("user", "2" , "1234567890"));

        LinearLayout container = (LinearLayout)findViewById(R.id.container);
        for (Contacts contact:contactses
             ) {
            TextView textView = new TextView(this);
            textView.setText(contact.firstName + " " + contact.lastName);

            container.addView(textView);


        }
    }
}
