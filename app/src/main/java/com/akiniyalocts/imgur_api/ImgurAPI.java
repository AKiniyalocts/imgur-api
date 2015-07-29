package com.akiniyalocts.imgur_api;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Basic;
import com.akiniyalocts.imgur_api.model.Image;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Interface representing the imgur api methods
 */
public interface ImgurAPI {

    @GET("/album/{id}")
    void getAlbumInfo(@Path("id") int id,
                      Callback<Album> cb);

    @GET("/album/{id}/images")
    void getAlbumImages(@Path("id") int albumId,
                        Callback<List<Image>> cb);

    @GET("/album/{id}/image/{id}")
    void getAlbumImage(@Path("id") int albumId,
                       @Path("id") int imageId,
                       Callback<Image> cb);

    @POST("/album")
    void createAlbum(@Body com.akiniyalocts.imgur_api.model.post.Album album,
                     Callback<Basic> cb);

    @PUT("/album/{album}")
    void updateAlbum(@Path("album") String idOrDeleteHash,
                     @Body com.akiniyalocts.imgur_api.model.post.Album album,
                     Callback<Basic> cb);
}
