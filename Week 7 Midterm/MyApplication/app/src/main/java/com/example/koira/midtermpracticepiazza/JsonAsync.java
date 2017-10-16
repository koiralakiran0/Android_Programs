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

class JsonAsync extends AsyncTask<String, Void, ArrayList<Result>>{

    private ArrayList<Result> results;
    private ArrayList<Genre> arrayList_genre;
    Genre oneGenre;
    Result oneResult;
    Context context;

    public JsonAsync(ArrayList<Result> keywords, Context context) {
        this.results = keywords;
        this.context = context;
        arrayList_genre = new ArrayList<>();
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
    protected ArrayList<Result> doInBackground(String... params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");
                JSONArray getGenres;
                JSONObject gotGenre;
                JSONObject result;

                JSONObject root = new JSONObject(json);
                JSONObject feeds = root.getJSONObject("feed");
                JSONArray results_json = feeds.getJSONArray("results");
                Log.d("demo", results_json.length()+"");

                for (int i = 0; i < results_json.length(); i++) {
                    //Log.d("demo", "started the results_json forloop");
                    result = results_json.getJSONObject(i);
                    oneResult = new Result(result.getString("name"));
                    //Log.d("demo", result.getString("name"));
                    oneResult.setArtistUrl(result.getString("artistUrl"));
                    oneResult.setArtistId(result.getString("artistId"));
                    oneResult.setArtistName(result.getString("artistName"));
                    oneResult.setArtworkUrl100(result.getString("artworkUrl100"));
                    oneResult.setCopyright(result.getString("copyright"));
                    oneResult.setId(result.getString("id"));
                    oneResult.setKind(result.getString("kind"));
                    //Log.d("demo", result.getString("kind"));
                    oneResult.setReleaseDate(result.getString("releaseDate"));
                    oneResult.setUrl(result.getString("url"));

                    getGenres = result.getJSONArray("genres");
                    //Log.d("demo", getGenres.length() + "");
                    for (int j = 0; j < getGenres.length(); j++) {
                        //Log.d("demo", "started the getGenres forloop");
                        gotGenre = getGenres.getJSONObject(j);
                        oneGenre = new Genre(gotGenre.getString("name"));
                        //Log.d("demo", oneGenre.toString());
                        oneGenre.setUrl(gotGenre.getString("url"));
                        oneGenre.setGenreId(gotGenre.getString("genreId"));
                        arrayList_genre.add(oneGenre);
                    }
                    Log.d("demo", oneResult.toString());
                    oneResult.setGenres(arrayList_genre);
                    results.add(oneResult);
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
        return results;
    }

    @Override
    protected void onPostExecute(ArrayList<Result> result) {
        if (results.size() > 0) {
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