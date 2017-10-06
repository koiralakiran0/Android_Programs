package com.example.koira.homework_3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by koira on 10/5/2017.
 */

public class GetAllQuestions extends AsyncTask<String, Void, ArrayList<Question>>{

    ArrayList<Question> questions;
    ProgressDialog loading_questions;
    Context context;
    Button starting_trivia;

    public GetAllQuestions(ArrayList<Question> questions, Context context, Button starting_trivia){
        this.questions = questions;
        this.context = context;
        this.starting_trivia = starting_trivia;
    }

    @Override
    protected void onPreExecute() {
        loading_questions = new ProgressDialog(context);
        loading_questions.setCancelable(false);
        loading_questions.setMessage("Loading Questions");
        loading_questions.setProgress(0);
        loading_questions.show();
        starting_trivia.setClickable(false);
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        loading_questions.setMessage("Photo Loaded");
        loading_questions.setProgress(100);
        loading_questions.dismiss();
        loading_questions.setProgress(0);

        starting_trivia.setClickable(true);
    }

    @Override
    protected ArrayList<Question> doInBackground(String... strings) {
        BufferedReader reader = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Log.d("demo", reader.readLine());

            String line;
            while ((line = reader.readLine()) != null){
                questions.add(new Question(line));
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }
}
