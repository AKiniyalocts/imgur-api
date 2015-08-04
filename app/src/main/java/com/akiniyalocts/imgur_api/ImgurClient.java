package com.akiniyalocts.imgur_api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.ImgurResponse;
import com.akiniyalocts.imgur_api.model.post.AlbumResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.mime.TypedFile;

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

    public static void initialize(String imgurClientID){
        Constants.setClientId(imgurClientID);
        getInstance();
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
    private static void setRestAdapter() {
        if (mRestAdapter == null) {
            //todo setup conditional for client || bearer auth
            //todo defaulted to client right now
            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", Constants.AUTH_CLIENT + Constants.getClientId());
                    request.addHeader("Accept", "application/json");
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

    /**p
     * @return our instance of our RestAdapter
     */
    private static RestAdapter getRestAdapter() {
        if (mRestAdapter != null) {
            return mRestAdapter;
        } else {
            setRestAdapter();

            return mRestAdapter;
        }
    }

    /**
     * @return our instance of our ImgurAPI
     */
    private static ImgurAPI getImgurAPI() {
        if (imgurAPI != null) {
            return imgurAPI;
        } else {
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
    public void getAlbumInfo(@NonNull String id, @NonNull Callback<ImgurResponse<Album>> cb) {
        getImgurAPI().getAlbumInfo(id, cb);
    }

    /**
     * Provides images list of all images in an album via callback
     *
     * @param albumId id of the album
     * @param cb      callback
     * @see com.akiniyalocts.imgur_api.model.Image
     */
    public void getAlbumImages(@NonNull String albumId, @NonNull Callback<ImgurResponse<List<Image>>> cb) {
        getImgurAPI().getAlbumImages(albumId, cb);
    }

    /**
     * Provides images list of all images in an album via callback
     *
     * @param albumId id of the album
     * @param imageId id of the image in the album
     * @param cb      callback
     * @see com.akiniyalocts.imgur_api.model.Image
     * @deprecated The API method will return a wrong response if the image
     * is not found within the album (success & status 200) this is no good practice.
     * Maybe you should use getImage()
     */
    public void getAlbumImage(@NonNull String albumId,
                              @NonNull String imageId,
                              @NonNull Callback<ImgurResponse<Image>> cb) {
        getImgurAPI().getAlbumImage(albumId, imageId, cb);
    }

    /**
     * Creates an album
     *
     * @param album post object of an album
     * @param cb    callback
     */
    public void createAlbum(@NonNull com.akiniyalocts.imgur_api.model.post.Album album,
                            @NonNull Callback<ImgurResponse<AlbumResponse>> cb) {
        getImgurAPI().createAlbum(album, cb);
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Updates an album
     *
     * @param album album
     * @param cb    callback
     */
    public void updateAlbum(@NonNull Album album,
                            @NonNull Callback<ImgurResponse> cb) {
        com.akiniyalocts.imgur_api.model.post.Album postAlbum =
                new com.akiniyalocts.imgur_api.model.post.Album(album);
        updateAlbum(postAlbum, album.getId(), album.getDeletehash(), cb);
    }

    /**
     * Updates an album, provide either deletehash or album id
     *
     * @param album      album
     * @param albumId    id of album
     * @param deleteHash deletehash of album
     * @param cb         callback
     */
    public void updateAlbum(@NonNull com.akiniyalocts.imgur_api.model.post.Album album,
                            String albumId,
                            String deleteHash,
                            @NonNull Callback<ImgurResponse> cb) {

        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNotNullOrEmpty(albumId)) {
            getImgurAPI().updateAlbum(albumId, album, cb);
        } else {
            getImgurAPI().updateAlbum(deleteHash, album, cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Deletes an album, provide on of both ids
     *
     * @param albumId    album id
     * @param deleteHash deletehash
     * @param cb         callback
     */
    public void deleteAlbum(String albumId, String deleteHash, @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");
        if (Util.isNotNullOrEmpty(deleteHash)) {
            getImgurAPI().deleteAlbum(deleteHash, cb);
        } else {
            getImgurAPI().deleteAlbum(albumId, cb);
        }
    }

    /**
     * Favorites an album
     *
     * @param albumId albumId
     * @param cb      callback
     */
    public void favoriteAlbum(@NonNull String albumId, @NonNull Callback<ImgurResponse> cb) {
        getImgurAPI().favoriteAlbum(albumId, cb);
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Sets images for an album
     *
     * @param album    album object
     * @param imageIds imageIds
     * @param cb       callback
     */
    public void setAlbumImages(@NonNull Album album,
                               @NonNull String[] imageIds,
                               @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            getImgurAPI().setAlbumImages(album.getId(), imageIds, cb);
        } else {
            getImgurAPI().setAlbumImages(album.getDeletehash(), imageIds, cb);
        }
    }

    /**
     * Sets images for an album
     * Provide either albumId or deleteHash
     *
     * @param albumId    album id
     * @param deleteHash deletehash for album
     * @param imageIds   imageIds
     * @param cb         callback
     */
    public void setAlbumImages(String albumId,
                               String deleteHash,
                               @NonNull String[] imageIds,
                               @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");

        if (Util.isNotNullOrEmpty(deleteHash)) {
            getImgurAPI().setAlbumImages(deleteHash, imageIds, cb);
        } else {
            getImgurAPI().setAlbumImages(albumId, imageIds, cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Sets images for an album
     *
     * @param album album object which includes imageIds
     * @param cb    callback
     */
    public void setAlbumImages(@NonNull Album album, @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            getImgurAPI().setAlbumImages(album.getId(), album.getImageIds(), cb);
        } else {
            getImgurAPI().setAlbumImages(album.getDeletehash(), album.getImageIds(), cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Add images to album
     *
     * @param album    album object
     * @param imageIds image ids which will be added to the album
     * @param cb       callback
     */
    public void addImagesToAlbum(@NonNull Album album,
                                 @NonNull String[] imageIds,
                                 @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            getImgurAPI().addImagesToAlbum(album.getId(), imageIds, cb);
        } else {
            getImgurAPI().addImagesToAlbum(album.getDeletehash(), imageIds, cb);
        }
    }

    /**
     * Add images to an album
     * Provide either albumId or deleteHash
     *
     * @param albumId    album id
     * @param deleteHash deletehash for album
     * @param imageIds   imageIds
     * @param cb         callback
     */
    public void addImagesToAlbum(String albumId,
                                 String deleteHash,
                                 @NonNull String[] imageIds,
                                 @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");

        if (Util.isNotNullOrEmpty(deleteHash)) {
            getImgurAPI().addImagesToAlbum(deleteHash, imageIds, cb);
        } else {
            getImgurAPI().addImagesToAlbum(albumId, imageIds, cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Add images to album
     *
     * @param album    album object
     * @param imageIds image ids which will be removed from the album
     * @param cb       callback
     */
    public void deleteImagesFromAlbum(@NonNull Album album,
                                      @NonNull String[] imageIds,
                                      @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            getImgurAPI().deleteImagesFromAlbum(album.getId(), imageIds, cb);
        } else {
            getImgurAPI().deleteImagesFromAlbum(album.getDeletehash(), imageIds, cb);
        }
    }

    /**
     * Deletes images from an album
     * Provide either albumId or deleteHash
     *
     * @param albumId    album id
     * @param deleteHash deletehash for album
     * @param imageIds   imageIds
     * @param cb         callback
     */
    public void deleteImagesFromAlbum(String albumId,
                                      String deleteHash,
                                      @NonNull String[] imageIds,
                                      @NonNull Callback<ImgurResponse> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");

        if (Util.isNotNullOrEmpty(deleteHash)) {
            getImgurAPI().deleteImagesFromAlbum(deleteHash, imageIds, cb);
        } else {
            getImgurAPI().deleteImagesFromAlbum(albumId, imageIds, cb);
        }
    }

    /**
     * Upload an image file to imgur
     *
     * @param imageFile file you wish to upload
     * @param title title of the image (Not required. Pass null if no title)
     * @param description description of the image. (Not required. Pass null if no title)
     * @param cb Callback for response
     */
    public void uploadImage(@NonNull TypedFile imageFile,
                            @Nullable String title,
                            @Nullable String description,
                            Callback<ImgurResponse<Image>> cb){
        getImgurAPI().anonymousImageUpload(imageFile, title, description, cb);
    }

    /**
     * Upload a base64 converted image to imgur
     *
     *
     * @param base64Image base64 converted image
     * @param title title of the image (Not required. Pass null if no title)
     * @param description description of the image. (Not required. Pass null if no title)
     * @param cb Callback for response
     */
    public void uploadImage(@NonNull Base64 base64Image,
                            @Nullable String title,
                            @Nullable String description,
                            Callback<ImgurResponse<Image>> cb){
        getImgurAPI().anonymousImageUpload(base64Image, title, description, cb);
    }

    /**
     * Upload a url of an image to imgur
     *
     * @param url url of image to upload
     * @param title title of the image (Not required. Pass null if no title)
     * @param description description of the image. (Not required. Pass null if no title)
     * @param cb Callback for response
     */
    public void uploadImage(@NonNull String url,
                            @Nullable String title,
                            @Nullable String description,
                            Callback<ImgurResponse<Image>> cb){
        getImgurAPI().anonymousImageUpload(url, title, description, cb);
    }
}