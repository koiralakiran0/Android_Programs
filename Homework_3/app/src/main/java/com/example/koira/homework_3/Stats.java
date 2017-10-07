package com.example.koira.homework_3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Stats extends AppCompatActivity {

    int numScores;
    int totalAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        numScores = 0;
        totalAns = 0;

        if (getIntent().getExtras() != null){
            numScores = getIntent().getExtras().getInt(Trivia.NUM_CORRECT_KEY);
            //Log.d("demo", numScores + "");
            totalAns = getIntent().getExtras().getInt(Trivia.TOTAL_KEY);
           // Log.d("demo", totalAns + "");
        }

        double actual_percent = (numScores * 100 )/totalAns;
       // Log.d("demo", actual_percent+"");

        ProgressBar progressBar_Correct = (ProgressBar) findViewById(R.id.progressBar_right);
        TextView textView_percent = (TextView) findViewById(R.id.textView_Percentage);
        TextView textView_Message = (TextView) findViewById(R.id.textView_message);
        Button button_Quit = (Button) findViewById(R.id.button_quit);
        Button button_tryAgain = (Button) findViewById(R.id.button_tryAgain);

        textView_percent.setText((int)Math.round(actual_percent) + "%");

        if (actual_percent == 100.0){
            textView_Message.setText("YOU ARE AMAZING. YOU GOT A 100! YOU CAN TRY AGAIN OR QUIT");
        }else
        {
            textView_Message.setText("Try again and see if you can get all the correct answers");
        }
        progressBar_Correct.setProgress((int) Math.round(actual_percent));

        button_Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Stats.this, MainActivity.class);
                //Removes all the Activities from Before
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        button_tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
