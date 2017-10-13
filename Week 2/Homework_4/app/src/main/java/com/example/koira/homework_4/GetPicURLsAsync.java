package com.example.koira.homework_4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

class GetPicURLsAsync extends AsyncTask<String, Void, ArrayList<String> > {

    private ArrayList<String> urls;
    private Context context;
    ImageView previous, next,showImage;
    ProgressDialog loadURLS;

    @Override
    protected void onPreExecute() {
        loadURLS = new ProgressDialog(context);
        loadURLS.setCancelable(false);

        loadURLS.setMessage("Loading URLs ...");
        loadURLS.setProgress(0);
        loadURLS.show();
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {

        if (urls.size() == 0){
            previous.setVisibility(View.INVISIBLE);
            next.setVisibility(View.INVISIBLE);
            showImage.setImageDrawable(null);
            Toast.makeText(context, "NO IMAGE ON THIS", Toast.LENGTH_SHORT).show();
        }
        if (urls.size() > 1){
            previous.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            new GetImages(showImage,context).execute(urls.get(0));
        }

        if (urls.size() == 1){
            new GetImages(showImage, context).execute(urls.get(0));
        }
            //Log.d("demo", result.toString());
            loadURLS.setMessage("Words Loaded!");
            loadURLS.setProgress(100);
            loadURLS.dismiss();
            loadURLS.setProgress(0);


    }

    public GetPicURLsAsync(ArrayList<String> image_urls, Context context, ImageView previous, ImageView next, ImageView showImage) {
        this.urls = image_urls;
        this.context = context;
        this.previous = previous;
        this.next = next;
        this.showImage = showImage;
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");
                JSONObject root = new JSONObject(json);
                JSONArray categories = root.getJSONArray("urls");

                if (categories != null) {
                    int len = categories.length();
                    for (int i=0;i<len;i++){
                        Log.d("demo", categories.toString());
                        urls.add(categories.getString(i));
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

        return urls;
    }
}
