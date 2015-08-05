package com.akiniyalocts.imgur_api;

import android.content.Context;
import android.content.Intent;

import com.akiniyalocts.imgur_api.ui.ImgurLoginActivity;

/**
 * Created by anthony on 7/28/15.
 */
public class Imgur {

    public static void launchLogin(Context context){
        Intent login = new Intent(context, ImgurLoginActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(login);
    }
}
