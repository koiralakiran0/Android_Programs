package com.example.koira.homework_3;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOError;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kiran on 10/6/17.
 */

public class GetImage extends AsyncTask<String, Void, Void> {
    ImageView imageView;
    Bitmap bitmap = null;
    ProgressDialog loading_photo;

    Context context;

    public GetImage(ImageView imageView, Context context){
        this.imageView = imageView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        loading_photo = new ProgressDialog(context);
        loading_photo.setCancelable(false);

        loading_photo.setMessage("Loading Image");
        loading_photo.setProgress(0);
        loading_photo.show();
        imageView.setImageBitmap(null);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        loading_photo.setMessage("Photo Loaded");
        loading_photo.setProgress(100);
        loading_photo.dismiss();
        loading_photo.setProgress(0);


        if (bitmap!= null && imageView != null){
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection connection = null;
        bitmap = null;

        if (strings[0] != "")
        try{
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{

        }
        return  null;
    }
}
