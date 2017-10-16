package com.example.koira.midtermpracticepiazza;

/*
The course midterm will be during our next lecture on 10/16/2017.
To prepare you should make sure you review all the assignments, and videos posted so far.
Also you should be comfortable to perform network calls and parsing both JSON and XML.
As an exercise I advise that you know how to parse the following JSON

https://rss.itunes.apple.com/api/v1/us/ios-apps/top-grossing/all/25/explicit.json
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Result> arrayList = new ArrayList<>();
    static int SECOND_CODE = 100;
    static String ARRAYLIST_CODE = "ARRAYLIST_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String api = "https://rss.itunes.apple.com/api/v1/us/ios-apps/top-grossing/all/25/explicit.json";


        findViewById(R.id.button_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    new JsonAsync(arrayList, MainActivity.this).execute(api);
                }
                else
                    Toast.makeText(MainActivity.this, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
        });

        final String xml_api = "https://rss.itunes.apple.com/api/v1/us/ios-apps/top-grossing/all/25/explicit.rss";
        findViewById(R.id.button_xml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()){
                    new ResultParser.ResultSaxParser();
                }
            }
        });
    }

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
