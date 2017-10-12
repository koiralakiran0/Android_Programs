package com.example.koira.homework_4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> keywords = new ArrayList<>();
    private ArrayList<String> image_urls = new ArrayList<>();

    EditText searchBar;
    ImageView showImage;
    private int photoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = (EditText) findViewById(R.id.editText_searchBar);
        showImage = (ImageView) findViewById(R.id.imageView_LoadImage);
        final ImageView imageView_previous = (ImageView) findViewById(R.id.imageView_Previous);
        final ImageView imageView_next = (ImageView) findViewById(R.id.imageView_Next);

        imageView_previous.setVisibility(View.INVISIBLE);
        imageView_next.setVisibility(View.INVISIBLE);

        new JsonAsync(keywords, this).execute("http://dev.theappsdr.com/apis/photos/keywords.php?format=json");

        findViewById(R.id.button_Go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isConnected()){
                    Toast.makeText(MainActivity.this, "Internet Available", Toast.LENGTH_SHORT).show();
                    //change arraylist to array
                    final String[] keywordArray = new String[keywords.size()];
                    for(int i = 0; i < keywordArray.length; i++) {
                        keywordArray[i] = keywords.get(i);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Choose a Keyword")
                            .setSingleChoiceItems(keywordArray,-1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    searchBar.setText(keywordArray[i]);
                                    image_urls = new ArrayList<String>();//reset
                                    photoIndex = 0; //set photoIndex back

                                    dialogInterface.dismiss();
                                    new GetPicURLsAsync(image_urls, MainActivity.this, imageView_previous, imageView_next).execute("http://dev.theappsdr.com/apis/photos/index.php?keyword=" + keywordArray[i] + "&format=json");
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else{
                    Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });


        imageView_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageView_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

}
