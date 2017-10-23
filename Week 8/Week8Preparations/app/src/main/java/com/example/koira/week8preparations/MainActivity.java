package com.example.koira.week8preparations;

import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String[] colors = {"Red","Green","Yellow","Blue","Orange","White","Black","Purple","Pink"
            ,"oooo","11111","22222","333333","444444","55555","66666",};

    Color[]  colorObjects = {new Color("Red"), new Color("Green"), new Color("Yellow"), new Color("Blue"), new Color("Orange"),
            new Color("White"), new Color("Black"), new Color("Purple"), new Color("Pink"), new Color("55555"),};

    ArrayList<Color> data = new ArrayList<>();
    ArrayAdapter<Color> adapter;

    ArrayList<Email> emails = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emails.add(new Email("Hi", "Summary 1", "bsmith@test.com"));
        emails.add(new Email("Hello", "Summary 2", "bsmith@test.com"));
        emails.add(new Email("Hi you", "Summary 3", "bsmith@test.com"));
        emails.add(new Email("Hi girl", "Summary 4", "bsmith@test.com"));
        emails.add(new Email("damn", "Summary 45", "bsmith@test.com"));
        emails.add(new Email("shi", "Summary 5", "bsmith@test.com"));
        emails.add(new Email("Hi", "Summary 6", "bsmith@test.com"));
/*
        data.add(new Color("Red"));
        data.add( new Color("Blue"));
        data.add( new Color("Green") );
        data.add( new Color("White"));
        data.add(new Color("Blue"));
*/
        ListView listView = (ListView) findViewById(R.id.listView);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
             //   android.R.id.text1, colors);
        //adapter = new ArrayAdapter<Color>(this, android.R.layout.simple_list_item_1,
               // android.R.id.text1, colorObjects);
        //adapter = new ArrayAdapter<Color>(this, android.R.layout.simple_list_item_1,
               // android.R.id.text1, data);
        EmailAdapter adapter = new EmailAdapter(this, R.layout.email_item, emails);
        listView.setAdapter(adapter);

        /*
        final EditText text = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add(new Color(text.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });

        //Respond to Clicks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d("demo", "Clicked item " + position + " + color " + colors[position]);
            }
        });

        //Responds to Long clicks
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Color color = adapter.getItem(position);
                adapter.remove(color);
                adapter.notifyDataSetChanged();
                return false;
            }
        });*/
    }

    static class Color{
        String name;
        int hex;

        public Color(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return "Color{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
