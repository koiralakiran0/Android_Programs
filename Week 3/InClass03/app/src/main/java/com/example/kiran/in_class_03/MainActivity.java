package com.example.kiran.in_class_03;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int REQ_CODE = 1;
    static final String VALUE_KEY = "whatever";
    static String USER_KEY = "USER";

    EditText edit_name;
    EditText edit_email;
    RadioGroup radioGroup;
    ImageView imageView;
    SeekBar seekBar;
    String avatar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        Button submit = (Button) findViewById(R.id.button_submit);
        final TextView currentMood = (TextView) findViewById(R.id.text_currentMood);
        imageView = (ImageView) findViewById(R.id.image_Select);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeView = new Intent(MainActivity.this, Select_Avatar.class);
                startActivityForResult(changeView, REQ_CODE);
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i== 0){
                    ImageView imageView = (ImageView) findViewById(R.id.image_mood);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.angry));
                    currentMood.setText("Current Mood : Angry");
                }else if (i==1){
                    ImageView imageView = (ImageView) findViewById(R.id.image_mood);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.sad));
                    currentMood.setText("Current Mood : Sad");
                }else if (i==2){
                    ImageView imageView = (ImageView) findViewById(R.id.image_mood);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                    currentMood.setText("Current Mood : Happy");
                }else if (i==3){
                    ImageView imageView = (ImageView) findViewById(R.id.image_mood);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.awesome));
                    currentMood.setText("Current Mood : Awesome");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "";
                String email = "";
                String department = "";
                String mood = "";


                edit_name = (EditText) findViewById(R.id.edit_Name);
                if (edit_name != null || edit_name.getText().toString() != "") {
                    name = edit_name.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "No name Entered", Toast.LENGTH_SHORT).show();
                }

                edit_email = (EditText) findViewById(R.id.editText2);
                if (edit_email != null || edit_email.getText().toString() != "" || validateEmail(edit_email.getText().toString())) {
                    email = edit_email.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "Email Invalid", Toast.LENGTH_SHORT).show();
                }

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

                if (R.id.radio_BIO == radioGroup.getCheckedRadioButtonId()){
                    department = "BIO";
                } else if ( R.id.radio_CIS == radioGroup.getCheckedRadioButtonId() ){
                    department = "CIS";
                } else if ( R.id.radio_SIS == radioGroup.getCheckedRadioButtonId() ){
                    department = "SIS";
                }

                int progress = seekBar.getProgress();
                if (progress == 0){
                    mood = "I am Angry!";
                } else if (progress == 1){
                    mood = "I am Sad!";
                } else if (progress == 2){
                    mood = "I am Happy!";
                } else if (progress == 3){
                    mood = "I am Awesome!";
                }

                Profile profile = new Profile(name, email, department, avatar, mood);

                //change View to Display Activity
                Intent intent = new Intent(MainActivity.this, Display_Activity.class);
                intent.putExtra(USER_KEY, profile);
                startActivity(intent);
            }
        });

        //int id = data.getExtras().getInt(Value_KEY)

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Check which request we're responding to
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                int id = intent.getExtras().getInt(VALUE_KEY);
                if (id == R.id.image_female1){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_1));
                    avatar = "R.drawable.avatar_f_1";
                }else if (id == R.id.image_female2){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_2));
                    avatar = "R.drawable.avatar_f_2";
                } else if (id == R.id.image_female3) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_3));
                    avatar = "R.drawable.avatar_f_3";
                } else if (id == R.id.image_male1){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_3));
                    avatar = "R.drawable.avatar_m_3";
                } else if (id == R.id.image_male2){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_2));
                    avatar = "R.drawable.avatar_m_2";
                }else if (id == R.id.image_male3){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_1));
                    avatar = "R.drawable.avatar_m_1";
                }
            }
        }
    }

    boolean validateEmail(String email){
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

}
