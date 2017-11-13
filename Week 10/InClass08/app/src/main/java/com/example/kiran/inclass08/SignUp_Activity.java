/*
In Class 08
Name: Kiran Koirala
Group 1
 */

package com.example.kiran.inclass08;

import android.content.Intent;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp_Activity extends AppCompatActivity {
    private EditText firstName_edittext;
    private EditText edit_lastName;
    private EditText edit_email;
    private EditText edit_password;
    private EditText password_repeat;

    String sEmail = "";
    String sPassword = "";

    TokenInfo tokenInfo;
    final OkHttpClient client = new OkHttpClient();
    final static String TOKEN_CODE = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        firstName_edittext = (EditText) findViewById(R.id.editText_firstname);
        edit_email = (EditText) findViewById(R.id.editText_email);
        edit_password = (EditText) findViewById(R.id.editText_password);
        edit_lastName = (EditText) findViewById(R.id.editText_lastName);
        password_repeat = (EditText) findViewById(R.id.editText_repeatPass);


        //SIGNUP BUTTON
        findViewById(R.id.button_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String repeat = password_repeat.getText().toString();
                //Log.d("demo", repeat);

                sEmail = edit_email.getText().toString();
                Log.d("demo", "email" + sEmail);
                sPassword = edit_password.getText().toString();
                Log.d("demo", "passwrod" + sPassword);

                if (!sPassword.equals(repeat)) {
                    Toast.makeText(SignUp_Activity.this, "Password don't match", Toast.LENGTH_SHORT).show();
                }else if (!validateEmail(sEmail)){
                    Toast.makeText(SignUp_Activity.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
                }else {
                    signUpUser(sEmail, sPassword, firstName_edittext.getText().toString(), edit_lastName.getText().toString());
                }
            }
        });

        //CANCEL LETS YOU GO BACK TO MAIN ACTIVITY
        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    boolean validateEmail(String email){
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public void signUpUser(String email, String password, String fname, String lname){
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("fname", fname)
                .add("lname", lname)
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/signup")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                firstName_edittext.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SignUp_Activity.this, "Failed Signup", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                    firstName_edittext.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignUp_Activity.this, "User Created", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Gson gson = new Gson();
                    tokenInfo = gson.fromJson(response.body().string(), TokenInfo.class);
                    //Log.d("demo", threadMessage.toString());

                    loginUser();
                } else{
                    firstName_edittext.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignUp_Activity.this, "SIGNUP FAILED", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void loginUser(){
        RequestBody formBody = new FormBody.Builder()
                .add("email", sEmail)
                .add("password", sPassword)
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/login")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                firstName_edittext.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SignUp_Activity.this, "Failed Login", Toast.LENGTH_SHORT).show();
                    }
                });
                //
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();

                    firstName_edittext.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignUp_Activity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Gson gson = new Gson();
                    tokenInfo = gson.fromJson(response.body().string(), TokenInfo.class);

                    Log.d("demo ", tokenInfo.toString());
                    startActivityChatScreen();
                } else{
                    firstName_edittext.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignUp_Activity.this, "Failed Login", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void startActivityChatScreen() {
        Intent intent = new Intent(SignUp_Activity.this, ActivityChatScreen.class);
        intent.putExtra(TOKEN_CODE, tokenInfo);
        //intent.putExtra("class", "signupactivity");
        startActivity(intent);
        finish();
    }

}
