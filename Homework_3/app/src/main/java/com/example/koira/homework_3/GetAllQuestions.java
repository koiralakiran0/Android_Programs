package com.example.koira.homework_3;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
    protected ArrayList<Question> doInBackground(String... strings) {
        StringTokenizer stringTokenizer = null;

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            stringTokenizer = new StringTokenizer(reader.readLine());
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return questions;
    }
}
