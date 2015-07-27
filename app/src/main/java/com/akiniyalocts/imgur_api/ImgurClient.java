package com.akiniyalocts.imgur_api;

import android.support.annotation.NonNull;

import com.akiniyalocts.imgur_api.model.Album;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Implementations for the Imgur REST interface.
 * Provides instance, setup and calls to the ImgurApi
 *
 * @see com.akiniyalocts.imgur_api.ImgurAPI
 */
public class ImgurClient {

    private RestAdapter mRestAdapter;
    private static ImgurClient instance;

    private ImgurClient() {
        setRestAdapter();
    }

    /**
     * Use to obtain instance of ImgurClient
     * Instance will be created if it was not created previously
     */
    public static ImgurClient getInstance() {
        if (instance == null) {
            instance = new ImgurClient();
        }
        return instance;
    }

    /**
     * Configures the restAdapter if its not configured yet
     * Sets endpoint and loglevel
     */
    private void setRestAdapter() {
        if (mRestAdapter == null) {
            //todo Set RequestInterceptor for api auth headers?
            RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
                    .setEndpoint(Constants.API_BASE_URL);

            //Set Loglevel to FULL if we're running a debug build
            if (BuildConfig.DEBUG)
                restAdapterBuilder.setLogLevel(RestAdapter.LogLevel.FULL);

            mRestAdapter = restAdapterBuilder.build();
        }
    }

    /**
     * Provides album object via callback
     *
     * @param id id of the album
     * @param cb callback
     * @see com.akiniyalocts.imgur_api.model.Album
     */
    public void getAlbumInfo(int id, @NonNull Callback<Album> cb) {
        ImgurAPI imgur = mRestAdapter.create(ImgurAPI.class);
        imgur.getAlbumInfo(id, cb);
    }
}
