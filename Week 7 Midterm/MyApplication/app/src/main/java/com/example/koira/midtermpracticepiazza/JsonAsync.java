package com.example.koira.midtermpracticepiazza;

/**
 * Created by koira on 10/14/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by koira on 10/9/2017.
 */

class JsonAsync extends AsyncTask<String, Void, ArrayList<String>>{

    private ArrayList<String> keywords;
    Context context;

    public JsonAsync(ArrayList<String> keywords, Context context) {
        this.keywords = keywords;
        this.context = context;
    }

    ProgressDialog loadWords;
    @Override
    protected void onPreExecute() {
        loadWords = new ProgressDialog(context);
        loadWords.setCancelable(false);

        loadWords.setMessage("Loading Words ...");
        loadWords.setProgress(0);
        loadWords.show();
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");
                JSONObject root = new JSONObject(json);
                JSONArray categories = root.getJSONArray("categories");

                if (categories != null) {
                    int len = categories.length();
                    for (int i=0;i<len;i++){
                        keywords.add(categories.getString(i));
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return keywords;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        if (result.size() > 0) {
            //Log.d("demo", result.toString());
            loadWords.setMessage("Words Loaded!");
            loadWords.setProgress(100);
            loadWords.dismiss();
            loadWords.setProgress(0);

        } else {
            Log.d("demo", "empty result");
        }
    }
}