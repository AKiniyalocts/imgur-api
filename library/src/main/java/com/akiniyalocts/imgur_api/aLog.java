package com.akiniyalocts.imgur_api;

import android.util.Log;

/**
 * Created by anthony on 7/28/15.
 */
public class aLog  {

    public static void w (String TAG, String msg){
        if(BuildConfig.DEBUG) {
            if (TAG != null && msg != null)
                Log.w(TAG, msg);
        }
    }
}
