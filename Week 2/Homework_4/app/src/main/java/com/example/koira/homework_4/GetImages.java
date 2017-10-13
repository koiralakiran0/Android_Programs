/**
 * Assignment #: Homework 4
 * File Name: GetImages.java
 * Group 1: Kiran Koirala
 */

package com.example.koira.homework_4;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by koira on 10/9/2017.
 */

class GetImages extends AsyncTask<String, Void, Void> {
    ImageView imageView;
    Bitmap bitmap = null;
    ProgressDialog loading_images;
    Context context;

    @Override
    protected void onPreExecute() {
        loading_images = new ProgressDialog(context);
        loading_images.setCancelable(false);

        loading_images.setMessage("Loading Image ...");
        loading_images.setProgress(0);
        loading_images.show();
    }

    public GetImages(ImageView showImage, Context context) {
        this.imageView = showImage;
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection connection = null;
        bitmap = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void voidThings) {
        if (bitmap != null && imageView != null) {
            loading_images.setMessage("Image Loaded");
            loading_images.setProgress(100);
            loading_images.dismiss();
            loading_images.setProgress(0);
            imageView.setImageBitmap(bitmap);
        }
    }
}
