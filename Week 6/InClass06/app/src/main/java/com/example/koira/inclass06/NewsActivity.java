package com.example.koira.inclass06;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    static final String URL_CODE = "GET_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (getIntent() != null && getIntent().getExtras() != null){
            Source source = (Source) getIntent().getExtras().getSerializable(MainActivity.SOURCE_KEY);
            setTitle(source.name);

            String urlParseLoad = "https://newsapi.org/v1/articles?source=" + source.id + "&apiKey=ec9966ed533540b19a63130f8a23dc75";
            new ArticlesAsync().execute(urlParseLoad);
        }
    }

    private class ArticlesAsync extends AsyncTask<String, Void, ArrayList<Article>> {
        ProgressDialog loadingArticle;
        Article article;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            articles = new ArrayList<>();
            loadingArticle = new ProgressDialog(NewsActivity.this);
            loadingArticle.setCancelable(false);

            loadingArticle.setMessage("Loading Articles....");
            loadingArticle.setProgress(0);
            loadingArticle.show();

        }

        @Override
        protected ArrayList<Article> doInBackground(String... params) {
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
                        article = new Article();
                        article.author = oneResult.getString("author");
                        article.title = oneResult.getString("title");
                        article.url = oneResult.getString("url");
                        article.urlToImage = oneResult.getString("urlToImage");
                        article.publishedAt = oneResult.getString("publishedAt");
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
        protected void onPostExecute(final ArrayList<Article> articless){
            if(articles.size()>0){
                loadingArticle.setMessage("Sources Loaded...");
                loadingArticle.setProgress(100);
                loadingArticle.dismiss();
                loadingArticle.setProgress(0);

                ListView listview = (ListView) findViewById(R.id.articles_container);
                ArticleAdapter adapter = new ArticleAdapter(NewsActivity.this, R.layout.article_item, articles);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(NewsActivity.this, Webview_Activity.class);
                        intent.putExtra(URL_CODE, articles.get(i).url);
                        startActivity(intent);
                    }
                });
            }else{
                Log.d("demo","empty result");
            }
        }
    }
}
