package com.akiniyalocts.imgur_api;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.Response;
import com.akiniyalocts.imgur_api.model.post.AlbumResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Interface representing the imgur api methods
 */
public interface ImgurAPI {

    @GET("/album/{id}")
    void getAlbumInfo(@Path("id") String id,
                      Callback<Response<Album>> cb);

    @GET("/album/{id}/images")
    void getAlbumImages(@Path("id") String albumId,
                        Callback<Response<List<Image>>> cb);

    @GET("/album/{albumId}/image/{imageId}")
    void getAlbumImage(@Path("albumId") String albumId,
                       @Path("imageId") String imageId,
                       Callback<Response<Image>> cb);

    @POST("/album")
    void createAlbum(@Body com.akiniyalocts.imgur_api.model.post.Album album,
                     Callback<Response<AlbumResponse>> cb);

    @PUT("/album/{album}")
    void updateAlbum(@Path("album") String idOrDeleteHash,
                     @Body com.akiniyalocts.imgur_api.model.post.Album album,
                     Callback<Response> cb);

    @DELETE("/album/{album}")
    void deleteAlbum(@Path("album") String idOrDeleteHash,
                     Callback<Response> cb);

    @POST("/album/{id}/favorite")
    void favoriteAlbum(@Path("id") String albumId,
                       Callback<Response> cb);

    @POST("/album/{album}")
    void setAlbumImages(@Path("album") String idOrDeleteHash,
                        @Body String[] imageIds,
                        Callback<Response> cb);

    @PUT("/album/{album}/add")
    void addImagesToAlbum(@Path("album") String idOrDeleteHash,
                          @Body String[] imageIds,
                          Callback<Response> cb);

    @DELETE("/album/{album}/remove_images")
    void deleteImagesFromAlbum(@Path("album") String idOrDeleteHash,
                               @Body String[] imageIds,
                               Callback<Response> cb);
}
