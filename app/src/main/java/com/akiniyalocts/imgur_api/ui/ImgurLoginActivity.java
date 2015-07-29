package com.akiniyalocts.imgur_api.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.akiniyalocts.imgur_api.Constants;
import com.akiniyalocts.imgur_api.Imgur;
import com.akiniyalocts.imgur_api.ImgurClient;
import com.akiniyalocts.imgur_api.R;
import com.akiniyalocts.imgur_api.aLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgurLoginActivity extends AppCompatActivity{
    private static final String TAG = ImgurLoginActivity.class.getSimpleName();

    private static final Pattern accessTokenPattern = Pattern.compile("access_token=([^&]*)");
    private static final Pattern refreshTokenPattern = Pattern.compile("refresh_token=([^&]*)");
    private static final Pattern expiresInPattern = Pattern.compile("expires_in=(\\d+)");

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgur_activity_login);

        initWebView();
    }

    private void initWebView(){
        mWebView = (WebView)findViewById(R.id.imgur_login_webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        aLog.w(TAG, Constants.getGeneratedAuthURL());

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                boolean tokensURL = false;

                if (url.startsWith(Constants.IMGUR_REDIRECT_URL)) {
                    tokensURL = true;

                    Matcher m;

                    m = refreshTokenPattern.matcher(url);
                    m.find();
                    String refreshToken = m.group(1);

                    m = accessTokenPattern.matcher(url);
                    m.find();
                    String accessToken = m.group(1);

                    m = expiresInPattern.matcher(url);
                    m.find();
                    long expiresIn = Long.valueOf(m.group(1));

                    //TODO: Save tokens to local storage somewhere ?
                    aLog.w(
                            TAG,
                            "Refresh token: " + refreshToken + "\n" +
                                    "Acccess token: " + accessToken + "\n" +
                                    "Expires in: " + expiresIn
                    );

                }
                return tokensURL;
            }
        });

        mWebView.loadUrl(Constants.getGeneratedAuthURL());

    }


}
