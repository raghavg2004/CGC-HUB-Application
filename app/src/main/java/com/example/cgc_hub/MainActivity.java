package com.example.cgc_hub;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize WebView and set its settings
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new CustomWebViewClient(this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://cgchub.vercel.app/");
    }

    // Handle navigation for back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

// Custom WebViewClient to handle internal links
class CustomWebViewClient extends WebViewClient {

    private final Activity activity;

    public CustomWebViewClient(Activity activity) {
        this.activity = activity;
    }

    // For API levels less than 24
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        return false; // Keep links within the app
    }

    // For API levels 24 and above
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
        return false; // Keep links within the app
    }
}
