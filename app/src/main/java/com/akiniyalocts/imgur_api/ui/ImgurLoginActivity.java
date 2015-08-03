package com.akiniyalocts.imgur_api.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.akiniyalocts.imgur_api.Constants;
import com.akiniyalocts.imgur_api.Imgur;
import com.akiniyalocts.imgur_api.ImgurClient;
import com.akiniyalocts.imgur_api.PreferenceHelper;
import com.akiniyalocts.imgur_api.R;
import com.akiniyalocts.imgur_api.aLog;
import com.akiniyalocts.imgur_api.auth.ImgurAuthorization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgurLoginActivity extends AppCompatActivity{
    private static final String TAG = ImgurLoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgur_activity_login);

        initWebView();
    }

    private void initWebView(){

        WebView mWebView = (WebView)findViewById(R.id.imgur_login_webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                boolean tokensURL = false;


                if (url.startsWith(Constants.IMGUR_REDIRECT_URL)) {
                    tokensURL = true;


                    if (tokensURL) {

                        ImgurAuthorization imgurAuthorization = new ImgurAuthorization(ImgurLoginActivity.this);

                        imgurAuthorization.saveTokens(url);


                        ImgurLoginActivity.this.finish();

                    }

                }
                return tokensURL;
            }
        });

        mWebView.loadUrl(Constants.getGeneratedAuthURL());

    }


}
