package com.example.koira.inclass06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Webview_Activity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_);

        webView = (WebView) findViewById(R.id.webview_view);

        if (getIntent() != null && getIntent().getExtras()!= null){
            String url = getIntent().getExtras().getString(NewsActivity.URL_CODE);

            webView.loadUrl(url);
        }
    }
}
