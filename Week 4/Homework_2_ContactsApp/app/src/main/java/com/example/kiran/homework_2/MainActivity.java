package com.example.kiran.homework_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_createNew = (Button) findViewById(R.id.button_create);
        Button button_edit = (Button) findViewById(R.id.button_edit);
        Button button_delete = (Button) findViewById(R.id.button_delete);
        Button button_display = (Button) findViewById(R.id.button_display);
        Button button_finish = (Button) findViewById(R.id.button_finish);

        button_createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Create_New_Contact.class);
                startActivity(intent);
            }
        });

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Contacts_List.class);
                startActivity(intent);
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Contacts_List.class);
                startActivity(intent);
            }
        });

        button_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Contacts_List.class);
                startActivity(intent);
            }
        });

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
