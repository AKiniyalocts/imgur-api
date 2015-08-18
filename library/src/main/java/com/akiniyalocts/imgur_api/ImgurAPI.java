package com.akiniyalocts.imgur_api;

import android.util.Base64;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.ImgurResponse;
import com.akiniyalocts.imgur_api.model.post.AlbumResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Interface representing the imgur api methods
 */
public interface ImgurAPI {

    @GET("/album/{id}")
    void getAlbumInfo(@Path("id") String id,
                      Callback<ImgurResponse<Album>> cb);

    @GET("/album/{id}/images")
    void getAlbumImages(@Path("id") String albumId,
                        Callback<ImgurResponse<List<Image>>> cb);

    @GET("/album/{albumId}/image/{imageId}")
    void getAlbumImage(@Path("albumId") String albumId,
                       @Path("imageId") String imageId,
                       Callback<ImgurResponse<Image>> cb);

    @POST("/album")
    void createAlbum(@Body com.akiniyalocts.imgur_api.model.post.Album album,
                     Callback<ImgurResponse<AlbumResponse>> cb);

    @PUT("/album/{album}")
    void updateAlbum(@Path("album") String idOrDeleteHash,
                     @Body com.akiniyalocts.imgur_api.model.post.Album album,
                     Callback<ImgurResponse> cb);

    @DELETE("/album/{album}")
    void deleteAlbum(@Path("album") String idOrDeleteHash,
                     Callback<ImgurResponse> cb);

    @POST("/album/{id}/favorite")
    void favoriteAlbum(@Path("id") String albumId,
                       Callback<ImgurResponse> cb);

    @POST("/album/{album}")
    void setAlbumImages(@Path("album") String idOrDeleteHash,
                        @Body String[] imageIds,
                        Callback<ImgurResponse> cb);

    @PUT("/album/{album}/add")
    void addImagesToAlbum(@Path("album") String idOrDeleteHash,
                          @Body String[] imageIds,
                          Callback<ImgurResponse> cb);

    @DELETE("/album/{album}/remove_images")
    void deleteImagesFromAlbum(@Path("album") String idOrDeleteHash,
                               @Body String[] imageIds,
                               Callback<ImgurResponse> cb);

    @POST("/image")
    void anonymousImageUpload(@Body TypedFile imageFile,
                              @Query("title") String title,
                              @Query("description") String description,
                              Callback<ImgurResponse<Image>> cb);

    @POST("/image")
    void anonymousImageUpload(@Body Base64 base64Image,
                              @Query("title") String title,
                              @Query("description") String description,
                              Callback<ImgurResponse<Image>> cb);

    @POST("/image")
    void anonymousImageUpload(@Body String url,
                              @Query("title") String title,
                              @Query("description") String description,
                              Callback<ImgurResponse<Image>> cb);

    @GET("/memegen/defaults")
    void getDefaultMemes(Callback<ImgurResponse<List<Image>>> cb);
}
