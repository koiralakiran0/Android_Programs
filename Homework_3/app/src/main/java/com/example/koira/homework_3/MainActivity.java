package com.example.koira.homework_3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url_question = "http://dev.theappsdr.com/apis/trivia_json/trivia_text.php";
        Button button_Exit = (Button) findViewById(R.id.button_Exit);
        Button button_Start = (Button) findViewById(R.id.button_Start);
        TextView text_ready = (TextView) findViewById(R.id.textView_Ready);
        text_ready.setVisibility(View.INVISIBLE);

        button_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Trivia.class);
                startActivity(intent);
            }
        });

        if (isConnected()){
            //INTERNET AVAILABLE LOAD FILE
            new GetAllQuestions(questions).execute(url_question);
        }
        else{
            Toast.makeText(MainActivity.this, "INTERNET UNAVAILABLE", Toast.LENGTH_SHORT).show();
        }

    }

    //IS IT CONNECTED TO INTERNET?
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

}
