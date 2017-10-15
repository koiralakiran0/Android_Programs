package com.example.koira.midtermpracticepiazza;

/*
The course midterm will be during our next lecture on 10/16/2017.
To prepare you should make sure you review all the assignments, and videos posted so far.
Also you should be comfortable to perform network calls and parsing both JSON and XML.
As an exercise I advise that you know how to parse the following JSON

https://rss.itunes.apple.com/api/v1/us/ios-apps/top-grossing/all/25/explicit.json
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String api = "https://rss.itunes.apple.com/api/v1/us/ios-apps/top-grossing/all/25/explicit.json";
        new JsonAsync(arrayList ,MainActivity.this).execute(api);
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
