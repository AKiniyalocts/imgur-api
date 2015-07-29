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

    private static RestAdapter mRestAdapter;
    private static ImgurClient instance;
    private static ImgurAPI imgurAPI;

    private ImgurClient() {
        setRestAdapter();
    }

    /**
     * Use to obtain instance of ImgurClient
     * Instance will be created if it was not created previously
     *
     * @param imgurClientID Client-ID provided from imgur
     */
    public static ImgurClient getInstance(String imgurClientID) {
        if (instance == null) {
            Constants.setClientId(imgurClientID);
            instance = new ImgurClient();
        }
        return instance;
    }

    /**
     * Configures the restAdapter if its not configured yet
     * Sets endpoint and loglevel
     */
    private static void setRestAdapter() {
        if (mRestAdapter == null) {
            //todo setup conditional for client || bearer auth
            //todo defaulted to client right now
            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(Constants.AUTH_CLIENT, Constants.getClientId());
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
     *
     * @return our instance of our RestAdapter
     */
    private static RestAdapter getRestAdapter(){
        if(mRestAdapter != null){
            return mRestAdapter;
        }
        else{
            setRestAdapter();

            return mRestAdapter;
        }
    }

    /**
     *
     * @return our instance of our ImgurAPI
     */
    private static ImgurAPI getImgurAPI(){
        if(imgurAPI != null){
            return imgurAPI;
        }
        else{
            imgurAPI = getRestAdapter().create(ImgurAPI.class);
            return imgurAPI;
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
        getImgurAPI().getAlbumInfo(id, cb);
    }

    /**
     * Provides images list of all images in an album via callback
     *
     * @param albumId id of the album
     * @param cb callback
     * @see com.akiniyalocts.imgur_api.model.Image
     */
    public void getAlbumImages(int albumId, @NonNull Callback<List<Image>> cb){
        getImgurAPI().getAlbumImages(albumId, cb);
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
        getImgurAPI().getAlbumImage(albumId, imageId, cb);
    }
}
