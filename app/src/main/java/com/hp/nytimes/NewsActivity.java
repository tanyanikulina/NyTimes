package com.hp.nytimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.hp.nytimes.tools.Tools;

public class NewsActivity extends AppCompatActivity {

    WebView wv;
    String surl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        wv = findViewById(R.id.wv);
        surl = getIntent().getStringExtra(Tools.INTENT_EXTRA_URL);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(surl);

    }
}
