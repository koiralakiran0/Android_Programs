package com.example.kiran.module4practice;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;

import java.net.URL;
import java.util.logging.LogRecord;

/**
 * Created by koira on 9/25/2017.
 */

/*
Code on OnCreate
public void onCreate(Bundle saveInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        handler = new Handler(new Handler.Callback(){
            if (msg.getData().containsKey("status")){
                String text = msg.getData().getString("status");
                ((TextView) findViewByID(R.id.textView1)).setText(text);
             }
             return true;
        }
});

Button b = (Button) findViewById(R.id.button1);
b.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v){
        new Thread(imageDownload, "Download Thread").start();
    }
});

OPTIONS
Messages
sendEmptyMessage(int);
sendMessage(Message)
sendMessageAtTime(Message, long);
sendMessageDelayed(Message, long);

Runnable
post(Runnable)
postAtTime(Runnable, long)
postDelayed(Runnable, long)

Handler h = new Handler();
h.postDelayed(new Runnable() {
    @Override
    public void run(){
        showDialog("Done");
} , 5000);
}
});

        */
public class Concurrency_Handler extends Activity{
    private Handler handler;
    private Runnable imageDownload = new Runnable() {

        public void sendMsg(String msgText){
            Bundle bundle = new Bundle();

            bundle.putString("status", msgText);
            Message message = new Message();
            message.setData(bundle);
           handler.sendMessage(message);
        }
        @Override
        public void run() {
            sendMsg("Starting Thread");
            try{
                URL url = new URL("http://www.uncc.edu/sites/default/files/spotlight/give-blood-no-text.jpg");
                Bitmap image = BitmapFactory.decodeStream(url.openStream());
                if (image != null){
                    sendMsg("File Retrieved");
                }else {
                    sendMsg("File Error");
                }
            } catch (Exception e){
                sendMsg("Failed Downloading");
                e.printStackTrace();
            }
        }
    };
}
