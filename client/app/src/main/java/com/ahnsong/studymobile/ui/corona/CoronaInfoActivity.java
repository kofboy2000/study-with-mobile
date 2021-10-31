package com.ahnsong.studymobile.ui.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.utils.ProgressDialog;

public class CoronaInfoActivity extends AppCompatActivity {
    private static final String TAG = "CoronaInfoActivity";

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_info);

        WebView mWebView = findViewById(R.id.activity_main_webview);
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mWebView, url);
                Log.d(TAG, "Loading done");
                mDialog.dismiss();
            }
        });
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);

        mDialog = new ProgressDialog(this);
        mDialog.show();
        String url = getResources().getString(R.string.notion_corona_info);
        mWebView.loadUrl(url);
    }
}