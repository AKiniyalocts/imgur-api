package com.akiniyalocts.imgur_api.auth;

import android.content.Context;
import android.util.Log;

import com.akiniyalocts.imgur_api.PreferenceHelper;
import com.akiniyalocts.imgur_api.Util;
import com.akiniyalocts.imgur_api.aLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anthony on 8/2/15.
 */
public class ImgurAuthorization {
    private final static String TAG = ImgurAuthorization.class.getSimpleName();

    private final Pattern accessTokenPattern = Pattern.compile("access_token=([^&]*)");
    private final Pattern refreshTokenPattern = Pattern.compile("refresh_token=([^&]*)");
    private final Pattern expiresInPattern = Pattern.compile("expires_in=(\\d+)");

    private Context context;
    private String url;


    public ImgurAuthorization(Context context){
        this.context = context;
    }

    /**
     * Save tokens from URL to shared preferences
     * @param url return URL from imgur OAuth
     */
    public void saveTokens(String url){
        extractTokensFromUrl(url);
    }

    /**
     *  Extract tokens from url parameters, save them off to local storage for easy access
     *
     */
    private void extractTokensFromUrl(String url){

        String refreshToken;
        String accessToken;
        long expiresIn;

        Matcher m;

        m = refreshTokenPattern.matcher(url);
        m.find();
        refreshToken = m.group(1);

        m = accessTokenPattern.matcher(url);
        m.find();
        accessToken = m.group(1);

        m = expiresInPattern.matcher(url);
        m.find();

        expiresIn = Long.valueOf(m.group(1));

        if(saveRefreshToken(refreshToken) && saveAccessToken(accessToken)){
            setRefreshTime(expiresIn);
        }

    }

    /**
     *
     * @param expiresIn time until refresh token will expire
     */
    private void setRefreshTime(long expiresIn){
        PreferenceHelper.writeRefreshTime(context, System.currentTimeMillis() + expiresIn);
    }

    private boolean saveRefreshToken(String refreshToken){
        if(tokenIsValid(refreshToken)){
            PreferenceHelper.writeRefreshToken(context, refreshToken);
            return true;
        }
        else
            return false;
    }

    private boolean saveAccessToken(String accessToken){
        if(tokenIsValid(accessToken)){
            PreferenceHelper.writeAccessToken(context, accessToken);
            return true;
        }
        else
            return false;
    }

    private boolean tokenIsValid(String token){
        return Util.isNullOrEmpty(token);
    }
}
