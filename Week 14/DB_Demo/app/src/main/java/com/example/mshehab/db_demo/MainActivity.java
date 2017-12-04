package com.example.mshehab.db_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBDataManager dbDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbDataManager = new DBDataManager(this);


        dbDataManager.getNoteDAO().save(new Note("Subject 1", "Text 1"));
        dbDataManager.getNoteDAO().save(new Note("Subject 2", "Text 2"));
        dbDataManager.getNoteDAO().save(new Note("Subject 3", "Text 3"));

        ArrayList<Note> notes = dbDataManager.getNoteDAO().getAll();

        Log.d("demo", "onCreate: " + notes.toString());

        dbDataManager.close();
    }
}
