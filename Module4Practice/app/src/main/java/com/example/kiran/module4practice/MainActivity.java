package com.example.kiran.module4practice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    //Handler handler;
    //ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView complexity = (TextView) findViewById(R.id.text_progress);
        Button thread = (Button) findViewById(R.id.button_numThread);
        Button async = (Button) findViewById(R.id.button_Async);
        TextView results = (TextView) findViewById(R.id.text_numResults);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                complexity.setText(progress + " Times");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Updating Progress");
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCancelable(false);
            }
        });

        async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*
        //VIDEO 1
        //SIMPLE THREAD
        Thread thread = new Thread(new DoWork()); //create thread
        thread.start(); //run

        //THREADPOOL
        ExecutorService taskPool = Executors.newFixedThreadPool(5);
        taskPool.execute(new DoWork());

        //THREADPOOL WITHIN THE MAIN CLASS OR ANONYMOUS CLASS
        //ADV: SIMPLE, CAN ACCESS ACTIVITY METHODS AND ATTRIBUTES
        //DIS: LIMITED FUNCTIOANALITY, NO PARAMETERS/SCALABILITY, HARD REUSABILITY
        taskPool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++){
                    for (int j = 0; j<100; j++){

                    }
                }
            }
        });

        //USING THIS AND RUNNABLE METHOD
        taskPool.execute(this);

    }

    @Override
    public void run(){

    }



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Progress");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d("demo", "Message Received.... " + msg.obj);
                switch (msg.what){
                    case DoWork.STATUS_START:
                        progressDialog.setProgress(0);
                        progressDialog.show();
                        Log.d("demo", "Starting...... ");
                        break;
                    case DoWork.STATUS_PROGRESS:
                        //Log.d("demo", "Progress...... " + msg.obj);
                        progressDialog.setProgress((Integer) msg.obj);
                        Log.d("demo", "Progress...... " + msg.getData().getInt(DoWork.PROGRESS_KEY));
                        break;
                    case DoWork.STATUS_STOP:
                        progressDialog.dismiss();
                        Log.d("demo", "Stopping...... ");
                        break;
                }
                return false;
            }
        });

        new Thread(new DoWork()).start();
    }
*/
        /*
    public class DoWork implements Runnable{
        int maxValue;

        static final int STATUS_START = 0x00;
        static final int STATUS_PROGRESS = 0x01;
        static final int STATUS_STOP = 0x02;
        static final String PROGRESS_KEY = "PROGRESS";

        public DoWork(){

        }
        public DoWork(int maxValue){
            this.maxValue = maxValue;
        }
        @Override
        public void run() {
            Message startMessage = new Message();
            startMessage.what =  STATUS_START;
            handler.sendMessage(startMessage);

            for (int i = 0; i < 100; i++){
                for (int j = 0; j<1000000; j++){
                }
                Message message = new Message();
                message.what = STATUS_PROGRESS;
                message.obj = (Integer)i;
                Bundle bundle = new Bundle();
                bundle.putInt(PROGRESS_KEY, (Integer)i);
                handler.sendMessage(message);
            }

            Message stopMessage = new Message();
        }
    }
}
*/
    }

    public class asyncClass extends AsyncTask<HeavyWork, Integer , Double>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Double aDouble) {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected  Double doInBackground(HeavyWork... params) {
            return null;
        }
    }
}