package com.example.jobfairapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class GoogleDocsActivity extends Activity {
	WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googledocs);
		
		mWebView= (WebView) findViewById(R.id.webView1);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.loadUrl("https://docs.google.com/document/d/17mISgUSvetZJbalCCo-1gOJw15VH_MS4rZKEhcX6Ny8/edit");

		


	}
	
	@Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }
	
}

/*
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return false;

*/
