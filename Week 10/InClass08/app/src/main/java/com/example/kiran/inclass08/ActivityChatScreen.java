package com.example.kiran.inclass08;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;

public class ActivityChatScreen extends AppCompatActivity {

    TokenInfo tokenInfo;
    private final OkHttpClient client = new OkHttpClient();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        listView = (ListView) findViewById(R.id.listView_container);

        if (getIntent()!= null && getIntent().getExtras() != null){
            tokenInfo = (TokenInfo) getIntent().getExtras().getSerializable(MainActivity.TOKEN_CODE);
            getTheadMessages();
        }
    }

    public void getTheadMessages(){
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread")
                .header("Authorization", "BEARER " + tokenInfo.getToken())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("demo", "onFailure: " + "error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    //Log.d("demo", "onResponse: " + response.body().string());

                    Gson gson = new Gson();
                    ThreadMessageResponse threadMessageResponse = gson.fromJson(response.body().string(), ThreadMessageResponse.class);
                    final ArrayAdapter<ThreadMessage> adapter = new ArrayAdapter<ThreadMessage>(ActivityChatScreen.this, android.R.layout.simple_list_item_1,
                           android.R.id.text1, threadMessageResponse.getThreads() );
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(adapter);
                        }
                    });

                    //Log.d("demo", "onResponse: " + threadMessageResponse.getThreads().toString());

                } else{
                    Log.d("demo", "onResponse: " + response.body().string());
                }
            }
        });

    }
}
