package com.akiniyalocts.imgurapitestapplication;

import android.app.Application;

import com.akiniyalocts.imgur_api.ImgurClient;

/**
 * Created by anthony on 7/27/15.
 */
public class ImgurTestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createImgurClient();
    }

    private void createImgurClient(){
        ImgurClient.initialize(getString(R.string.client_id));
    }
}
