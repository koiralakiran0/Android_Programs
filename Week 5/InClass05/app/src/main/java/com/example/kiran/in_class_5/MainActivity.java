package com.example.kiran.in_class_5;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView_Display;
    ImageView imageView_picRetrieved, imageView_previous, imageView_next;
    Button button_go;
    ArrayList<String> urls;
    int currentPhoto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView_Display = (TextView) findViewById(R.id.textView_Display);
        imageView_picRetrieved = (ImageView) findViewById(R.id.imageView_display);
        imageView_previous = (ImageView) findViewById(R.id.imageView_previous);
        imageView_next = (ImageView) findViewById(R.id.imageView_next);
        button_go = (Button) findViewById(R.id.button_Go);
        urls = new ArrayList<>();

        imageView_previous.setVisibility(View.INVISIBLE);
        imageView_next.setVisibility(View.INVISIBLE);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    Toast.makeText(MainActivity.this, "Internet Connection", Toast.LENGTH_SHORT).show();
                    new GetKeywords().execute("http://dev.theappsdr.com/apis/photos/keywords.php");
                } else {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imageView_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPhoto == 0){
                    currentPhoto = urls.size() -1;
                } else {
                    currentPhoto--;
                }

                new GetImages(imageView_picRetrieved).execute(urls.get(currentPhoto));
            }
        });

        imageView_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPhoto == urls.size()-1){
                    currentPhoto = 0;
                } else {
                    currentPhoto++;
                }

                new GetImages(imageView_picRetrieved).execute(urls.get(currentPhoto));
            }
        });
    }


    private class GetKeywords extends AsyncTask<String, Void, String>{
        @Override
        protected void onPostExecute(String s) {
            final String[] toAlertDialog = s.split(";");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Choose a Keyword")
                    .setItems(toAlertDialog, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            textView_Display.setText(toAlertDialog[i]);

                            urls = new ArrayList<>();
                            currentPhoto = 0;

                            new GetLinksAsync().execute("http://dev.theappsdr.com/apis/photos/index.php");


                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                }

                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }

                if (inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
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

    private class GetLinksAsync extends AsyncTask<String, Void, ArrayList<String>>{
        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            if (urls.size() >= 1){
                new GetImages(imageView_picRetrieved).execute(urls.get(0));
                if (urls.size() > 1){
                    imageView_next.setVisibility(View.VISIBLE);
                    imageView_previous.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(MainActivity.this, "NO URLS", Toast.LENGTH_SHORT).show();
                imageView_picRetrieved.setImageDrawable(null);
                imageView_previous.setVisibility(View.INVISIBLE);
                imageView_next.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                String stringUrl = strings[0] + "?" + "keyword=" + textView_Display.getText().toString();

                URL url = new URL(stringUrl);


                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line = "";
                while((line = reader.readLine()) != null){
                    urls.add(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return urls;

        }
    }
}
