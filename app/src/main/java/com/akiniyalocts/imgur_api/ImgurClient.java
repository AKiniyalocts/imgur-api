package com.akiniyalocts.imgur_api;

import android.support.annotation.NonNull;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
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
            //todo setup conditional for client || bearer auth
            //todo defaulted to client right now
            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(Constants.AUTH_CLIENT, Constants.CLIENT_ID);
                }
            };


            RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
                    .setEndpoint(Constants.API_BASE_URL);

            //Set Loglevel to FULL if we're running a debug build
            if (BuildConfig.DEBUG)
                restAdapterBuilder.setLogLevel(RestAdapter.LogLevel.FULL);

            restAdapterBuilder.setRequestInterceptor(requestInterceptor);

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

    /**
     * Provides images list of all images in an album via callback
     *
     * @param albumId id of the album
     * @param cb callback
     * @see com.akiniyalocts.imgur_api.model.Image
     */
    public void getAlbumImages(int albumId, @NonNull Callback<List<Image>> cb){
        ImgurAPI imgur = mRestAdapter.create(ImgurAPI.class);
        imgur.getAlbumImages(albumId, cb);
    }

    /**
     * Provides images list of all images in an album via callback
     *
     * @param albumId id of the album
     * @param imageId id of the image in the album
     * @param cb callback
     * @see com.akiniyalocts.imgur_api.model.Image
     */
    public void getAlbumImage(int albumId, int imageId, @NonNull Callback<Image> cb){
        ImgurAPI imgur = mRestAdapter.create(ImgurAPI.class);
        imgur.getAlbumImage(albumId, imageId, cb);
    }
}
