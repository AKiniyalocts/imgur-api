package com.akiniyalocts.imgur_api;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anthony on 7/11/15.
 *
 * Helper to write/read shared prefs
 */
public class PreferenceHelper {
    private static final String PREF_NAME = "com.akiniyalocts.imgur-api";

    private static final String KEY_REFRESH_TOKEN = "key::refresh:token";
    private static final String KEY_ACCESS_TOKEN = "key::access:token";
    private static final String KEY_REFRESH_TIME = "key::refresh:time";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getRefreshToken(Context context){
        return getPreferences(context).getString(KEY_REFRESH_TOKEN, null);
    }

    public static void writeRefreshToken(Context context, String refreshToken){
        getPreferences(context).edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply();
    }

    public static String getAccessToken(Context context){
        return getPreferences(context).getString(KEY_ACCESS_TOKEN, null);
    }

    public static void writeAccessToken(Context context, String accessToken){
        getPreferences(context).edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public static long getRefreshTime(Context context){
        return getPreferences(context).getLong(KEY_REFRESH_TIME, 0);
    }

    public static void writeRefreshTime(Context context, long refreshTime){
        getPreferences(context).edit().putLong(KEY_REFRESH_TIME, refreshTime).apply();
    }


}
