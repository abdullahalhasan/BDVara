package com.droidsoftbd.bdvara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Home extends AppCompatActivity {

    private WebView webView;
    private ConnectionDetector detector;
    private AdView mAdView;
    private ProgressBar mProgressBar;
    static String mainURL = "http://bdvara.com";
    private String droidsoftURL = "http://droidsoftbd.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MobileAds.initialize(this, String.valueOf(R.string.ad_unit));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        webInit();

    }

    public void webInit() {
        webView = (WebView) findViewById(R.id.bdVaraWV);
        detector = new ConnectionDetector(Home.this);
        WebSettings webSettings = webView.getSettings();


        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        loadPortal();
    }

    public void loadPortal() {
        //webView.setWebViewClient(new myWebViewClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mainURL);
    }

    public void droidsoft(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(droidsoftURL));
        startActivity(intent);
    }

    public class myWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (detector.isConnected() == true) {
                //mProgressBar.setVisibility(View.VISIBLE);
                view.loadUrl(mainURL);
                //loadPortal();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(mainURL);
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == event.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
