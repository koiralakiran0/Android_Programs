/*
In Class 08
Name: Kiran Koirala
Group 1
 */

package com.example.kiran.inclass08;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TokenInfo tokenInfo;
    final OkHttpClient client = new OkHttpClient();
    final static String TOKEN_CODE = "info";
    EditText emailEditText;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //LOGIN BUTTON
        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailEditText = (EditText) findViewById(R.id.editText_email);
                String email = emailEditText.getText().toString();
                EditText passwordEditText = (EditText) findViewById(R.id.editText_password);
                String password = passwordEditText.getText().toString();

                loginUser(email, password);
            }
        });

        //SIGNUP BUTTON
        findViewById(R.id.button_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });

    }

    //LOGIN USING USERNAME AND PASSWORD
    public void loginUser(String username, String password){
        RequestBody formBody = new FormBody.Builder()
                .add("email", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/login")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                emailEditText.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();
                    }
                });
                //
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();

                    emailEditText.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Gson gson = new Gson();
                    tokenInfo = gson.fromJson(response.body().string(), TokenInfo.class);

                    Intent intent = new Intent(MainActivity.this, ActivityChatScreen.class);
                    intent.putExtra(TOKEN_CODE, tokenInfo);
                    intent.putExtra("class", "mainactivity");
                    startActivity(intent);
                } else{
                    emailEditText.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }



}
