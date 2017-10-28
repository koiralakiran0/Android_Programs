
/*
        * Assignment #: Homework 5
        * File Name: MainActivity.java
        * Name: Kiran Koirala
* */

package com.example.koira.homework_5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Result> results;
    EditText search;
    ListView listView;
    PodcastListAdapter adapter;
    public static final String OBJECT_KEY = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = new ArrayList<>();
        search = (EditText) findViewById(R.id.editText_search);

        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setText("");
                adapter.sort(new CompareDates());
                adapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.button_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.sort(new CompareResults(search.getText().toString()));
            }
        });

        if (isConnected()){
            Toast.makeText(this, "CONNECTED", Toast.LENGTH_SHORT).show();
            String xml_api = "https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml";
            new Xml_Sync().execute(xml_api);
        } else {
            Toast.makeText(this, "NOT CONNECTED", Toast.LENGTH_SHORT).show();
        }

    }

    private class Xml_Sync extends AsyncTask<String, Void, ArrayList<Result>>{
        ProgressDialog progressdialog;

        @Override
        protected void onPreExecute() {
            progressdialog = new ProgressDialog(MainActivity.this);
            progressdialog.setCancelable(false);

            progressdialog.setMessage("Parsing XML");
            progressdialog.setProgress(0);
            progressdialog.show();
        }

        @Override
        protected ArrayList<Result> doInBackground(String... params) {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    results = ResultParser.ResultSaxParser.parseResult(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (SAXException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return results;
        }

        @Override
        protected void onPostExecute(ArrayList<Result> result) {
            if (result.size() > 0) {
                progressdialog.setMessage("PARSING DONE");
                progressdialog.setProgress(100);
                progressdialog.dismiss();
                progressdialog.setProgress(0);
                //Log.d("demo", results.toString());

                listView = (ListView) findViewById(R.id.listView_container);
                adapter = new PodcastListAdapter(MainActivity.this, R.layout.result_item, results);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Result result_get = results.get(position);
                        Intent intent = new Intent(MainActivity.this, PodcastDetails.class);
                        intent.putExtra(OBJECT_KEY, result_get);
                        startActivity(intent);
                    }
                });

            } else {
                Toast.makeText(MainActivity.this, "NO RESULT", Toast.LENGTH_SHORT).show();
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

