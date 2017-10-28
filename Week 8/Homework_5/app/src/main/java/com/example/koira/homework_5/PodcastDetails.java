package com.example.koira.homework_5;

/*
        * Assignment #: Homework 5
        * File Name: PodcastDetails.java
        * Name: Kiran Koirala
* */
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class PodcastDetails extends AppCompatActivity {

    private ImageView imageView_image;
    private TextView summary;
    private TextView title;
    private TextView updatedDate;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_details);

        if (getIntent() != null && getIntent().getExtras() != null){
            result = (Result) getIntent().getSerializableExtra(MainActivity.OBJECT_KEY);
        }

        imageView_image = (ImageView) findViewById(R.id.imageView_image);
        summary = (TextView) findViewById(R.id.textView_summary);
        title = (TextView) findViewById(R.id.textView_title);
        updatedDate = (TextView) findViewById(R.id.textView_Updated);

        title.setText(result.getTitle());

        try {
            updatedDate.setText("Last Updated: " + result.getUpdatedDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(result.getLargeImage().trim()).into(imageView_image);
        summary.setText(result.getSummary());
    }
}
