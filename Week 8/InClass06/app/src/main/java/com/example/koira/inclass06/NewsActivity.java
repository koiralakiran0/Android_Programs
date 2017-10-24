/*
Assignment #: IN Class 6
FileName: NewsActivity.java
Name: Kiran Koirala
 */

package com.example.koira.inclass06;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ArrayList<Article> articles;
    Article article;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ////https://newsapi.org/v1/articles?source=bbc-news&apiKey=ec9966ed533540b19a63130f8a23dc75
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        articles = new ArrayList<>();

        if (getIntent() != null || getIntent().getExtras() != null){
            Source source = (Source) getIntent().getExtras().getSerializable(MainActivity.intentKey);
            setTitle(source.toString());

            String urlParseLoad = "https://newsapi.org/v1/articles?source=" + source.getId() + "&apiKey=ec9966ed533540b19a63130f8a23dc75";
            //Log.d("demo", urlParseLoad);
            new ArticlesAsync().execute(urlParseLoad);
        }
    }

    //THIS IS FOR LOADING ARTICLES
    public class ArticlesAsync extends AsyncTask<String, Void, ArrayList<Article>> {
        ProgressDialog loadingArticle;
        Article article;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingArticle = new ProgressDialog(NewsActivity.this);
            loadingArticle.setCancelable(false);

            loadingArticle.setMessage("Loading Articles");
            loadingArticle.setProgress(0);
            loadingArticle.show();
        }

        @Override
        protected ArrayList<Article> doInBackground(String...params){
            HttpURLConnection connection=null;

            try{
                URL url=new URL(params[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    String json= IOUtils.toString(connection.getInputStream(),"UTF8");
                    JSONObject root=new JSONObject(json);
                    JSONArray jsonArticles = root.getJSONArray("articles");
                    JSONObject oneResult;

                    for(int i=0;i<jsonArticles.length();i++){
                        oneResult = jsonArticles.getJSONObject(i);
                        article = new Article(oneResult.getString("author"), oneResult.getString("title"),
                                oneResult.getString("url"), oneResult.getString("urlToImage"), oneResult.getString("publishedAt"));
                        articles.add(article);

                    }
                }
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }catch(JSONException e){
                e.printStackTrace();
            }finally{
                if(connection!=null){
                    connection.disconnect();
                }
            }
            return articles;
        }

        @Override
        protected void onPostExecute(ArrayList<Article> articless){
            if(articles.size()>0){
                loadingArticle.setMessage("Sources Loaded");
                loadingArticle.setProgress(100);
                loadingArticle.dismiss();
                loadingArticle.setProgress(0);

                //Log.d("demo", articles.toString());

                ListView listview = (ListView) findViewById(R.id.articles_container);
                ArticleAdapter adapter = new ArticleAdapter(NewsActivity.this, R.layout.article_item, articles);
                listview.setAdapter(adapter);
            }else{
                Log.d("demo","empty result");
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
