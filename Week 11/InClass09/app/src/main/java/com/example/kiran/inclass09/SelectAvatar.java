package com.example.kiran.inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectAvatar extends AppCompatActivity {

    ImageView imageView_female1, imageView_female2, imageView_female3, imageView_male1, imageView_male2, imageView_male3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        imageView_female1 = (ImageView) findViewById(R.id.image_female1);
        imageView_female2 = (ImageView) findViewById(R.id.image_female2);
        imageView_female3 = (ImageView) findViewById(R.id.image_female3);
        imageView_male1 = (ImageView) findViewById(R.id.image_male1);
        imageView_male2 = (ImageView) findViewById(R.id.image_male2);
        imageView_male3 = (ImageView) findViewById(R.id.image_male3);

        imageView_female1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(CreateNewContact.GET_IMAGE_TAG, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        imageView_female2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(CreateNewContact.GET_IMAGE_TAG, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        imageView_female3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(CreateNewContact.GET_IMAGE_TAG, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        imageView_male1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(CreateNewContact.GET_IMAGE_TAG, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        imageView_male2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(CreateNewContact.GET_IMAGE_TAG, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        imageView_male3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(CreateNewContact.GET_IMAGE_TAG, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        //imageView_female3.setOnClickListener();
    }
}
