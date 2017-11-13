package com.example.kiran.in_class_03;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        //if (getIntent().getExtras() != null || getIntent() != null){
           Profile profile = (Profile) getIntent().getExtras().getSerializable(MainActivity.USER_KEY);
            Log.d("demo", profile.toString());

            text_name.setText("Name: " + profile.getName());
            text_email.setText("Email: " + profile.getEmail());
           text_department.setText("Department: " + profile.getDepartment());
           String mood = profile.getMood();
            text_feel.setText(mood);

            if (mood.equals("I am Angry!")){
                image_feel.setImageDrawable(getResources().getDrawable(R.drawable.angry));
            } else if (mood.equals("I am Sad!")){
                image_feel.setImageDrawable(getResources().getDrawable(R.drawable.sad));
            } else if (mood.equals("I am Happy!")){
                image_feel.setImageDrawable(getResources().getDrawable(R.drawable.happy));
            } else if (mood.equals("I am Awesome!")){
                image_feel.setImageDrawable(getResources().getDrawable(R.drawable.awesome));
            }

            String avatar = profile.getAvatar();
            if (avatar.equals("R.drawable.avatar_f_1")){
                avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_1));
            } else if (avatar.equals("R.drawable.avatar_f_2")){
                avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_2));
            } else if (avatar.equals("R.drawable.avatar_f_3")){
                avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_3));
            } else if (avatar.equals("R.drawable.avatar_m_1")){
                avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_1));
            } else if (avatar.equals("R.drawable.avatar_m_2")){
                avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_2));
            } else if (avatar.equals("R.drawable.avatar_m_3")){
                avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_3));
            }
            //avatar_image.setImageDrawable(getResources().getDrawable(avatar));

        }
    //}
}
