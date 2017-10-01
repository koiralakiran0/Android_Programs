package com.example.kiran.in_class_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Select_Avatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__avatar);

        ImageView female1 = (ImageView) findViewById(R.id.image_female1);
        ImageView female2 = (ImageView) findViewById(R.id.image_female2);
        ImageView female3 = (ImageView) findViewById(R.id.image_female3);
        ImageView male1 = (ImageView) findViewById(R.id.image_male1);
        ImageView male2 = (ImageView) findViewById(R.id.image_male2);
        ImageView male3 = (ImageView) findViewById(R.id.image_male3);

        female1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(MainActivity.VALUE_KEY, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        female2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(MainActivity.VALUE_KEY, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        female3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(MainActivity.VALUE_KEY, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        male1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(MainActivity.VALUE_KEY, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        male2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(MainActivity.VALUE_KEY, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });
        male3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(MainActivity.VALUE_KEY, view.getId());
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
