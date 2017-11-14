package com.example.kiran.inclass09;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class CreateNewContact extends AppCompatActivity {

    private ImageView imageView_select;
    private EditText editText_Name;
    private EditText editText_email;
    private EditText editText_phone;
    private RadioGroup radioGroup;
    private Button button_submit;
    private final static int TAG = 00000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);

        imageView_select = (ImageView) findViewById(R.id.image_Select);
        editText_Name = (EditText) findViewById(R.id.edit_Name);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_phone = (EditText) findViewById(R.id.editText_Phone);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button_submit = (Button) findViewById(R.id.button_submit);

        imageView_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNewContact.this, SelectAvatar.class);
                startActivityForResult(intent, TAG);
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
