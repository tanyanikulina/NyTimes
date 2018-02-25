package com.hp.nytimes.tools;

import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Map;

/**
 * Created by Hp on 25.02.2018.
 */

public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        return super.shouldOverrideUrlLoading(view, request);
    }

    WebResourceRequest req = new WebResourceRequest() {
        @Override
        public Uri getUrl() {
            return null;
        }

        @Override
        public boolean isForMainFrame() {
            return false;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public boolean hasGesture() {
            return false;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public Map<String, String> getRequestHeaders() {
            return null;
        }
    };

}
