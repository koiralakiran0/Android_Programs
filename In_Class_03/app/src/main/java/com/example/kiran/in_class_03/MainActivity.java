package com.example.kiran.in_class_03;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final ImageView selectImage = (ImageView) findViewById(R.id.image_Select);
        Button submit = (Button) findViewById(R.id.button_submit);
        final TextView currentMood = (TextView) findViewById(R.id.text_currentMood);

        selectImage.setOnClickListener(new View.OnClickListener() {
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
                    currentMood.setText(currentMood.getText().toString() + " angry");
                }else if (i==1){
                    ImageView imageView = (ImageView) findViewById(R.id.image_mood);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.sad));
                    currentMood.setText(currentMood.getText().toString() + " sad");
                }else if (i==2){
                    ImageView imageView = (ImageView) findViewById(R.id.image_mood);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                    currentMood.setText(currentMood.getText().toString() + " happy");
                }else if (i==3){
                    ImageView imageView = (ImageView) findViewById(R.id.image_mood);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.awesome));
                    currentMood.setText(currentMood.getText().toString() + " awesome");
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
                //Check if each are valid
                EditText edit_name = (EditText) findViewById(R.id.edit_Name);
                String name = edit_name.getText().toString();

                EditText edit_email = (EditText) findViewById(R.id.editText2);
                String email = edit_email.getText().toString();

                String department = "";
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

                int id = radioGroup.getCheckedRadioButtonId();
                View RadioButton = radioGroup.findViewById(id);
                int radioID = radioGroup.indexOfChild(RadioButton);
                RadioButton btn = (RadioButton) radioGroup.getChildAt(radioID);
                department = btn.getText().toString();

                String avatar = R.id.image_Select + "";

                String mood = seekBar.getProgress() + "";
                Profile profile = new Profile(name, email, department, avatar, mood);

                if (name ==  null || name == ""){
                    Toast.makeText(MainActivity.this, "INVALID", Toast.LENGTH_SHORT).show();
                }


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
                    ImageView imageView = (ImageView) findViewById(R.id.image_Select);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_1));

                }else if (id == R.id.image_female2){
                    ImageView imageView = (ImageView) findViewById(R.id.image_Select);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_2));
                } else if (id == R.id.image_female3) {
                    ImageView imageView = (ImageView) findViewById(R.id.image_Select);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_3));
                } else if (id == R.id.image_male1){
                    ImageView imageView = (ImageView) findViewById(R.id.image_Select);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_1));
                } else if (id == R.id.image_male2){
                    ImageView imageView = (ImageView) findViewById(R.id.image_Select);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_2));
                }else if (id == R.id.image_male3){
                    ImageView imageView = (ImageView) findViewById(R.id.image_Select);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_3));
                }
            }
        }
    }
}
