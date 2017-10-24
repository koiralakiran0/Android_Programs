/*
Assignment #: IN Class 6
FileName: MainActivity.java
Name: Kiran Koirala
 */

package com.example.koira.inclass06;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Source> sources;
    ListView listview;
    static String intentKey = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sources = new ArrayList<>();
        listview = (ListView) findViewById(R.id.listView_container);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra(intentKey, sources.get(position));
                startActivity(intent);
            }
        });

        String sourceFile = "https://newsapi.org/v1/sources";
        if (isConnected()){
            Toast.makeText(this, "CONNECTED", Toast.LENGTH_SHORT).show();
            new JSONAsync().execute(sourceFile);
        } else {
            Toast.makeText(this, "NOT CONNECTED", Toast.LENGTH_SHORT).show();
        }
    }


    public class JSONAsync extends AsyncTask<String, Void, ArrayList<Source>> {
        ProgressDialog loadingJson;
        Source source;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingJson = new ProgressDialog(MainActivity.this);
            loadingJson.setCancelable(false);

            loadingJson.setMessage("Loading Sources");
            loadingJson.setProgress(0);
            loadingJson.show();
        }

        @Override
        protected ArrayList<Source> doInBackground(String...params){
            HttpURLConnection connection=null;

            try{
                URL url=new URL(params[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    String json= IOUtils.toString(connection.getInputStream(),"UTF8");

                    JSONObject root=new JSONObject(json);
                    //JSONObject feed = root.getJSONObject("feed");
                    JSONArray jsonResults = root.getJSONArray("sources");
                    JSONObject oneResult;

                    for(int i=0;i<jsonResults.length();i++){
                        oneResult = jsonResults.getJSONObject(i);
                        source = new Source(oneResult.getString("id"), oneResult.getString("name"));
                        sources.add(source);
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
            return sources;
        }

        @Override
        protected void onPostExecute(ArrayList<Source> sources){
            if(sources.size()>0){
                loadingJson.setMessage("Sources Loaded");
                loadingJson.setProgress(100);
                loadingJson.dismiss();
                loadingJson.setProgress(0);

                ArrayAdapter<Source> adapter = new ArrayAdapter<Source>(MainActivity.this, android.R.layout.simple_list_item_1,
                        android.R.id.text1, sources);
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
