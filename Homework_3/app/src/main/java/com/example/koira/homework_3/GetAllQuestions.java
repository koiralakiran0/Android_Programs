package com.example.koira.homework_3;

import android.os.AsyncTask;
import android.util.Log;

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

    public GetAllQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        super.onPostExecute(questions);
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

<<<<<<< HEAD
            
=======
>>>>>>> 963bd1ded83b5ba33fd19882dc913e60322126d9
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
