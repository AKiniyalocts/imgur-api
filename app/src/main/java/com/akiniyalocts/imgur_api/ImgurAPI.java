package com.akiniyalocts.imgur_api;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by anthony on 7/26/15.
 */
public interface ImgurAPI {

    @GET("/album/{id}")
    void getAlbumInfo(@Path("id") int id, Callback<Album> cb);

    @GET("/album/{id}/images")
    void getAlbumImages(@Path("id") int albumId, Callback<List<Image>> cb);

    @GET("/album/{id}/image/{id}")
    void getAlbumImage(@Path("id") int albumId, @Path("id") int imageId, Callback<Image> cb);
}
