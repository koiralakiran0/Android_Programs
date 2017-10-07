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

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    ExecutorService taskPool = Executors.newFixedThreadPool(2);
    SeekBar seekbar_password_count, seekbar_length;
    ArrayList<String> passwords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textCount = (TextView) findViewById(R.id.text_count);
        final TextView textLength = (TextView) findViewById(R.id.text_length);
        final TextView text_result = (TextView) findViewById(R.id.text_resultPassword);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_thread);

        seekbar_password_count = (SeekBar) findViewById(R.id.seek_passwordcount);
        seekbar_length = (SeekBar) findViewById(R.id.seekBar_length);

        Button button_thread = (Button) findViewById(R.id.button_thread);
        Button button_async = (Button) findViewById(R.id.button_Async);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                //msgs.add( message.obj.toString());
                Log.d("demo", (String) message.obj);
                passwords.add((String)message.obj);

                text_result.setText("Password: " + message.obj.toString());

                Log.d("demo", "Password Length " +passwords.size() );



                progressBar.setMax(seekbar_password_count.getProgress());
                progressBar.setProgress(passwords.size());
                if(passwords.size() == seekbar_password_count.getProgress() ){
                    Log.d("demo" , "Done!!");
                }
                return false;
            }
        });
/*
final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final String[] items = (String[]) passwords.toArray();
        builder.setTitle("Pick a Password")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text_result.setText(items[i]);
                    }
                })
                .setCancelable(false);

        builder.create();
        text_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });
*/
/*
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Progress");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
*/

        //progressBar.setProgress(0);

        button_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = seekbar_length.getProgress();
                int number = seekbar_password_count.getProgress();
                passwords.clear();

               // progressBar.setMax(number);
                for (int i = 0; i <= number; i++){
                    taskPool.execute(new ThreadRun(length));
                   // progressBar.setProgress(i);
                }

            }
        });


        button_async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int length = seekbar_length.getProgress();
                int number = seekbar_password_count.getProgress();
                passwords.clear();

                for (int i = 0; i <= number; i++){
                    new AsyncClass().execute(length);
                }
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

        int length;
        public ThreadRun(int length){
            this.length = length + 8;
        }
        @Override
        public void run() {
            String msg = Util.getPassword(length);
            Message message = new Message();
            message.obj = msg;
            handler.sendMessage(message);
        }
    }

    public class AsyncClass extends AsyncTask<Integer, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            String message = Util.getPassword(integers[0] + 8);
            Message message1 = new Message();
            message1.obj = message;
            handler.sendMessage(message1);
            return null;
        }
    }
}
