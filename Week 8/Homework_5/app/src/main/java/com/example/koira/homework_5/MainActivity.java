
/*
        * Assignment #: Homework 5
        * File Name: MainActivity.java
        * Name: Kiran Koirala
* */

package com.example.koira.homework_5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isConnected()){
            Toast.makeText(this, "CONNECTED", Toast.LENGTH_SHORT).show();
            String xml_api = "https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml";
            new Xml_Sync().execute(xml_api);
        } else {

        }

    }

    private class Xml_Sync extends AsyncTask<String, Void, ArrayList<Result>>{
        @Override
        protected ArrayList<Result> doInBackground(String... params) {
            HttpURLConnection connection = null;
            ArrayList<Result> result = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    result = ResultPullParser.parseResults(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } //catch (SAXException e) {
            //e.printStackTrace();}
            catch (XmlPullParserException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Result> result) {
            if (result != null) {
                Log.d("demo", result.toString());
            } else {
                Log.d("demo", "null result");
            }
        }

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

