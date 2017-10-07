package com.example.kiran.in_class_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Display_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_);

        TextView text_name = (TextView) findViewById(R.id.Text_Name);
        TextView text_email = (TextView) findViewById(R.id.Text_email);
        TextView text_department = (TextView) findViewById(R.id.text_department);
        TextView text_feel = (TextView) findViewById(R.id.text_feeling);
        ImageView avatar_image = (ImageView) findViewById(R.id.Image_Photo);
        ImageView image_feel = (ImageView) findViewById(R.id.image_feeling);

        Profile profile = (Profile) getIntent().getExtras().getSerializable(MainActivity.USER_KEY);

        text_name.setText("Name: " + profile.getName());
        text_email.setText("Email: " + profile.getEmail());
        text_department.setText("Department: " + profile.getDepartment());

        int moodIndex = Integer.parseInt(profile.getMood());

        if (moodIndex == 0){
            text_feel.setText("I am so Angry!");
            image_feel.setImageDrawable(getResources().getDrawable(R.drawable.angry));
        }else if (moodIndex ==  1){
            text_feel.setText("I am so Sad");
            image_feel.setImageDrawable(getResources().getDrawable(R.drawable.sad));
        }else if (moodIndex ==  2){
            text_feel.setText("I am so Happy");
            image_feel.setImageDrawable(getResources().getDrawable(R.drawable.happy));
        }else if (moodIndex ==  3){
            text_feel.setText("I am so Awesome");
            image_feel.setImageDrawable(getResources().getDrawable(R.drawable.awesome));
        }
        else{
            //THIS IS WRONG
        }

        int avatar_ID = Integer.parseInt(profile.getAvatar());
        avatar_image.setImageDrawable(getResources().getDrawable(avatar_ID));
    }
}
