package com.example.kiran.inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;

public class ActivityChatScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        if (getIntent()!= null && getIntent().getExtras() != null){

        }
    }

    public void getTheadMessages(){
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread")
                .header("Authorization", "BEARER " + tokenInfo.token)
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
                    Log.d("demo", "onResponse: " + threadMessageResponse.threads.toString());


                } else{
                    Log.d("demo", "onResponse: " + response.body().string());
                }
            }
        });

    }

}
