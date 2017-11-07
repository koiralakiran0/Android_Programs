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
    EditText firstName_edittext;
    EditText lastName;
    EditText email;
    EditText password;
    EditText password_repeat;

    ThreadMessage threadMessage;
    final OkHttpClient client = new OkHttpClient();
    final String SIGNUP_CODE = "SIGNUP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        findViewById(R.id.button_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName_edittext = (EditText) findViewById(R.id.editText_firstname);
                lastName = (EditText) findViewById(R.id.editText_lastName);
                email = (EditText) findViewById(R.id.editText_email);
                String textEmail = email.getText().toString();
                password = (EditText) findViewById(R.id.editText_password);
                password_repeat = (EditText) findViewById(R.id.editText_repeatPass);

                if (password.getText().toString().equals(password_repeat.getText().toString()) && validateEmail(textEmail)){
                    signUpUser(firstName_edittext.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());
                }else {
                    Toast.makeText(SignUp_Activity.this, "PUT VALID INPUT", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    public void signUpUser(String firstName, String lastName, String email, String password){
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("fName", firstName)
                .add("lName", lastName)
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
                    threadMessage = gson.fromJson(response.body().string(), ThreadMessage.class);

                    Intent intent = new Intent(SignUp_Activity.this, ActivityChatScreen.class);
                    intent.putExtra(SIGNUP_CODE, threadMessage);
                    startActivity(intent);
                } else{
                    firstName_edittext.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignUp_Activity.this, "User Not Created", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
