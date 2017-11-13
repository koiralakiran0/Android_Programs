/*
In Class 08
Name: Kiran Koirala
Group 1
 */

package com.example.kiran.inclass08;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;

public class ActivityChatScreen extends AppCompatActivity {
    TokenInfo tokenInfo;
    private final OkHttpClient client = new OkHttpClient();
    ListView listView;
    ArrayAdapter<MessageThread> adapter = null;

    TextView textView_name;
    ImageView imageView_logout;
    ImageView imageView_add;
    EditText editText_newThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        listView = (ListView) findViewById(R.id.listView_container);

        textView_name = (TextView) findViewById(R.id.textview_name);
        imageView_logout = (ImageView) findViewById(R.id.imageView_logout);
        imageView_add = (ImageView) findViewById(R.id.imageView_add);

        if (getIntent()!= null && getIntent().getExtras() != null){
                tokenInfo = (TokenInfo) getIntent().getExtras().getSerializable(MainActivity.TOKEN_CODE);
                Log.d("active", tokenInfo.toString());
                getTheadMessages();
        }

        textView_name.setText(tokenInfo.getUser_fname() + " " + tokenInfo.getUser_lname());
        imageView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tokenInfo = null;
                finish();
            }
        });

        imageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewThread();
            }
        });
    }

    private void addNewThread() {
        editText_newThread = (EditText) findViewById(R.id.edit_newThread);

        MessageThread messageThread = new MessageThread(tokenInfo.getUser_fname(),
                tokenInfo.getUser_lname(), tokenInfo.getUser_email(), "id",
                editText_newThread.getText().toString(),"xx");
        //adapter.add(new MessageThread(tokenInfo.getUser_fname(),
               // tokenInfo.getUser_lname(), tokenInfo.getUser_email(),));
        Toast.makeText(ActivityChatScreen.this, editText_newThread.getText().toString() + " Thread Created", Toast.LENGTH_SHORT).show();
        adapter.add(messageThread);
        adapter.notifyDataSetChanged();
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

                    Gson gson = new Gson();
                    ThreadMessageResponse threadMessageResponse = gson.fromJson(response.body().string(), ThreadMessageResponse.class);
                    adapter = new ArrayAdapter<MessageThread>( ActivityChatScreen.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, threadMessageResponse.getThreads() );

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
