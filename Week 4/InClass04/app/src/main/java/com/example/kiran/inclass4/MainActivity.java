package com.example.kiran.inclass4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    ExecutorService taskPool = Executors.newFixedThreadPool(2);
    SeekBar seekbar_password_count, seekbar_length;
    String[] passwords;
    TextView textCount, textLength, textResult;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCount= (TextView) findViewById(R.id.text_count);
        textLength = (TextView) findViewById(R.id.text_length);
        textResult = (TextView) findViewById(R.id.text_resultPassword);
        progressDialog = new ProgressDialog(this);
        seekbar_password_count = (SeekBar) findViewById(R.id.seek_passwordcount);
        seekbar_length = (SeekBar) findViewById(R.id.seekBar_length);

        Button button_thread = (Button) findViewById(R.id.button_thread);
        Button button_async = (Button) findViewById(R.id.button_Async);

        progressDialog.setMessage("Generating Passwords");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                if (message.what == ThreadRun.STATUS_PROGRESS){
                    progressDialog.setMessage("Generating passwords ");
                    progressDialog.setProgress((int)message.obj);
                }else if (message.what == ThreadRun.STATUS_START){
                    progressDialog.setMessage("Generating passwords ");
                    progressDialog.setProgress(0);
                    progressDialog.show();
                } else if (message.what == ThreadRun.STATUS_STOP){
                    progressDialog.setMessage("Finished Generating Passwords!");
                    progressDialog.dismiss();
                    progressDialog.setProgress(0);


                    AlertDialog.Builder passwordPicker = new AlertDialog.Builder(MainActivity.this);
                    passwordPicker.setTitle("Passwords")
                            .setSingleChoiceItems(passwords,-1, new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    textResult.setText("Password: " +passwords[i]);
                                    dialogInterface.dismiss();
                                }
                            });

                    final AlertDialog alert = passwordPicker.create();
                    alert.show();

                }

                return false;
            }
        });

        button_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = seekbar_length.getProgress();
                int number = seekbar_password_count.getProgress();

                new ThreadRun(length,number).run();

            }
        });


        button_async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int length = seekbar_length.getProgress();
                int number = seekbar_password_count.getProgress();
                passwords = new String[number];

                new AsyncClass(length, number).execute();
            }
        });


        final int x = 1;
        seekbar_password_count.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i+x;
                textCount.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final int y = 8;
        seekbar_length.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i + y;
                textLength.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public class ThreadRun implements Runnable{

        static final int STATUS_START = 0x00;
        static final int STATUS_PROGRESS = 0x01;
        static final int STATUS_STOP = 0x02;

        int length;
        int number;

        public ThreadRun(int length, int number){
            this.length = length + 8;
            this.number = number + 1;
        }
        @Override
        public void run() {
            Message message = new Message();
            message.what = STATUS_START;
            handler.sendMessage(message);

            Toast.makeText(MainActivity.this, "Here", Toast.LENGTH_SHORT).show();
            passwords = new String[number];

            for (int i = 0; i < number; i++){
                passwords[i] = Util.getPassword(length);

                message = new Message();
                message.obj = ((Integer)((i+1)*100/number));

                message.what = STATUS_PROGRESS;
                handler.sendMessage(message);
                Toast.makeText(MainActivity.this, "x" + i, Toast.LENGTH_SHORT).show();
            }

            message = new Message();
            message.what = STATUS_STOP;
            handler.sendMessage(message);
        }
    }

    public class AsyncClass extends AsyncTask<Integer, Integer, String>{
        ProgressDialog generatePassword;

        int length, number;
        int cnt = 0;

        public AsyncClass(int length, int number) {
            this.length = length + 8;
            this.number = number +1;
            passwords = new String[this.number];
        }

        @Override
        protected void onPreExecute() {
            generatePassword = new ProgressDialog(MainActivity.this);
            generatePassword.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            generatePassword.setCancelable(false);
            cnt = 0;
            generatePassword.setMessage("Generating Passwords...");
            generatePassword.setProgress(0);
            generatePassword.show();
        }

        @Override
        protected void onPostExecute(String s) {
            generatePassword.setMessage("Passwords Generated!!");
            generatePassword.setProgress(100);
            generatePassword.dismiss();
            generatePassword.setProgress(0);

            AlertDialog.Builder passwordPicker = new AlertDialog.Builder(MainActivity.this);
            passwordPicker.setTitle("Passwords")
                    .setSingleChoiceItems(passwords,-1, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            textResult.setText("Password: " + passwords[i]);
                            dialogInterface.dismiss();
                        }
                    });

            final AlertDialog alert = passwordPicker.create();
            alert.show();
        }


        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < number; i++){
                passwords[i] = Util.getPassword(length);
                cnt++;
                progressDialog.setProgress( (cnt * 100) / number);
            }

            return 100 + "";
        }
    }
}
