package com.example.kiran.baccalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edit_Weight  = (EditText) findViewById(R.id.edit_weight);
        final ToggleButton gender = (ToggleButton) findViewById(R.id.toggle_gender);
        final Button save = (Button)findViewById(R.id.button_Save);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        final SeekBar alcohol_seekbar = (SeekBar) findViewById(R.id.seekBar);
        alcohol_seekbar.incrementProgressBy(5);
        final TextView textPercent = (TextView) findViewById(R.id.text_percentage);
        final Button addDrink = (Button) findViewById(R.id.button_adddrink);
        Button reset = (Button) findViewById(R.id.button_Reset);
        final TextView bac_level = (TextView) findViewById(R.id.text_BAC_level);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView results = (TextView) findViewById(R.id.text_result);

        //#4. saving
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDrink.setEnabled(true);
                save.setEnabled(true);
                radioGroup.check(R.id.radio_1oz);
                alcohol_seekbar.setProgress(0);
                textPercent.setText("0%");
                edit_Weight.setText("");
                bac_level.setText("BAC Level: ");
                results.setText("");
            }
        });

        //adding drinks
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String null_weight = edit_Weight.getText().toString();
                if (null_weight.matches("")){
                    Toast.makeText(getApplicationContext(), "No weight Entered", Toast.LENGTH_SHORT).show();
                    return;
                }
                //GENDER VALUES
                double r_gender;
                if (gender.isChecked()){
                    r_gender = .68;
                }else
                    r_gender= .55;

                //Weight Values
                double weight = Double.parseDouble(edit_Weight.getText().toString());

                //RADIOBUTTON VALUES
                int drink_size = 0;
                if (radioGroup.getCheckedRadioButtonId() == R.id.radio_1oz){
                    drink_size = 1;
                }else if (radioGroup.getCheckedRadioButtonId() == R.id.radio_5oz){
                    drink_size = 5;
                }else if (radioGroup.getCheckedRadioButtonId() == R.id.radio_12oz){
                    drink_size = 12;
                }

                //Seekbar Value
                int seekbar_value = alcohol_seekbar.getProgress();

                double current_BAC = ((drink_size*seekbar_value*6.24) / (weight*r_gender*100));//*100 for alcohol percent
                String text_BAC = bac_level.getText().toString();
                double accumulator = Double.parseDouble(text_BAC.substring(11)) + current_BAC;
                if (accumulator > 0.25)
                    accumulator = 0.25;
                bac_level.setText("BAC Level: " + String.format("%.2f", accumulator));
                int progress = (int) accumulator * 100;
                progressBar.setProgress(progress);


                if (accumulator <= 0.08){
                    results.setText("You're safe");
                    results.setBackgroundColor(Color.GREEN);
                }else if (accumulator <= 0.20){
                    results.setText("Be careful...");
                    results.setBackgroundColor(Color.YELLOW);
                }else if (accumulator < 0.25){
                    results.setText("Over the Limit");
                    results.setBackgroundColor(Color.RED);
                }else{
                    Toast.makeText(getApplicationContext(), "OVER LIMIT", Toast.LENGTH_SHORT).show();
                    save.setEnabled(false);
                    addDrink.setEnabled(false);
                }



            }
        });

        //Seekbar Control

        alcohol_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //textPercent.setText(i);
                i = i /5;
                i = i *5;
                textPercent.setText(i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
