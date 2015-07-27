package com.akiniyalocts.imgur_api;

import com.akiniyalocts.imgur_api.model.Album;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by anthony on 7/26/15.
 */
public interface ImgurAPI {

    @GET("/album/{id}")
    void getAlbumInfo(@Path("id") int id, Callback<Album> cb);

}
