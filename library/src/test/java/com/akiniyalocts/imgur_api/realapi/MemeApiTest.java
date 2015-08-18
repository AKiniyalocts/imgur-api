package com.akiniyalocts.imgur_api.realapi;

import com.akiniyalocts.imgur_api.ImgurClient;
import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.ImgurResponse;
import com.akiniyalocts.imgur_api.model.enums.AlbumLayout;
import com.akiniyalocts.imgur_api.model.post.AlbumResponse;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static org.junit.Assert.assertEquals;

/**
 * Well, some "real life" testing so we dont need an activity which executes the actions every time.
 */
public class MemeApiTest {

    private static ImgurClient client;
    private Result GetAlbumInfoResult = new Result();
    private Result GetAlbumImagesResult = new Result();
    private Result GetAlbumImageResult = new Result();
    private Result CreateAlbumResult = new Result();
    private Result UpdateAlbumResult = new Result();
    private Result DeleteAlbumResult = new Result();
    private Result SetAlbumImagesResult = new Result();
    private Result AddAlbumImagesResult = new Result();
    private Result DeleteAlbumImagesResult = new Result();

    private static final int TIMEOUT = 3500;
    public String mAlbumDeleteHash = "";

    @BeforeClass
    public static void init() {
        ImgurClient.initialize("3efbb6d75f6524f");
        client = ImgurClient.getInstance();
    }

    @Test
    public void GetAlbumInfo_Should_Succeed_Album_Exists() throws InterruptedException {
        MyCallback<ImgurResponse<Album>> cb
                = new MyCallback<>(GetAlbumInfoResult);

        client.getAlbumInfo("VZtsj", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumInfoResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumInfoResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    public void getAlbumInfo_Should_Succeed_Album_Not_Existant() throws InterruptedException {
        MyCallback<ImgurResponse<Album>> cb
                = new MyCallback<>(GetAlbumInfoResult);

        client.getAlbumInfo("xxxxxxx", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumInfoResult.SUCCESS_HAS_BEEN_CALLED, false);
        assertEquals(GetAlbumInfoResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    @Test
    public void getAlbumImages_Should_Load_Images() throws InterruptedException {
        MyCallback<ImgurResponse<List<Image>>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImages("VZtsj", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    public void getAlbumImages_Should_Fail_Album_Not_Found() throws InterruptedException {
        MyCallback<ImgurResponse<List<Image>>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImages("xxxxxxxx", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, false);
        assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    @Test
    public void getAlbumImage_Should_Succeed_Image_And_Album_Found() throws InterruptedException {
        MyCallback<ImgurResponse<Image>> cb
                = new MyCallback<>(GetAlbumImageResult);

        client.getAlbumImage("VZtsj", "VpXOjOE", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumImageResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumImageResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    @Ignore
    public void getAlbumImage_Should_Succeed_Image_Not_Found() throws InterruptedException {
        MyCallback<ImgurResponse<Image>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImage("VZtsj", "xxxxx", cb);

        Thread.sleep(TIMEOUT);

        //this is wrong! But the api returns status code 200 and success!
        assertEquals(GetAlbumImageResult.SUCCESS_HAS_BEEN_CALLED, true);
        //assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    @Test
    public void createAlbum_Should_Succeed() throws InterruptedException {
        com.akiniyalocts.imgur_api.model.post.Album album = new com.akiniyalocts.imgur_api.model.post.Album();
        album.setTitle("some title");
        album.setDescription("some description");
        album.setLayout(AlbumLayout.HORIZONTAL);

        AlbumCallback cb = new AlbumCallback(CreateAlbumResult);

        client.createAlbum(album, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(CreateAlbumResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(CreateAlbumResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void updateAlbum_Should_Succeed() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();
        com.akiniyalocts.imgur_api.model.post.Album album = new com.akiniyalocts.imgur_api.model.post.Album();
        album.setTitle("some title2");
        album.setDescription("some description2");
        album.setLayout(AlbumLayout.BLOG);

        MyCallback<ImgurResponse> cb
                = new MyCallback<>(UpdateAlbumResult);

        client.updateAlbum(album, "", mAlbumDeleteHash, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(UpdateAlbumResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(UpdateAlbumResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void updateAlbum_Should_Fail_Album_Not_Found() throws InterruptedException {
        com.akiniyalocts.imgur_api.model.post.Album album = new com.akiniyalocts.imgur_api.model.post.Album();
        album.setTitle("some title2");

        MyCallback<ImgurResponse> cb
                = new MyCallback<>(UpdateAlbumResult);

        client.updateAlbum(album, "", "blablabla", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(UpdateAlbumResult.FAILURE_HAS_BEEN_CALLED, true);
        assertEquals(UpdateAlbumResult.SUCCESS_HAS_BEEN_CALLED, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAlbum_Should_Fail_Exception_No_Id_And_Deletehash_Given() {
        com.akiniyalocts.imgur_api.model.post.Album album = new com.akiniyalocts.imgur_api.model.post.Album();

        MyCallback<ImgurResponse> cb
                = new MyCallback<>(new Result());

        client.updateAlbum(album, "", "", cb);
    }

    @Test
    public void deleteAlbum_Should_Succeed() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();

        MyCallback<ImgurResponse> cb
                = new MyCallback<>(DeleteAlbumResult);

        client.deleteAlbum("", mAlbumDeleteHash, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(DeleteAlbumResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(DeleteAlbumResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void deleteAlbum_Should_Fail_Album_Not_Found() throws InterruptedException {
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(DeleteAlbumResult);

        client.deleteAlbum("", "blablabla", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(DeleteAlbumResult.FAILURE_HAS_BEEN_CALLED, true);
        assertEquals(DeleteAlbumResult.SUCCESS_HAS_BEEN_CALLED, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteAlbum_Should_Fail_Exception_No_AlbumId_And_Deletehash_Given() {
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(new Result());

        client.deleteAlbum(null, "", cb);
    }

    @Test
    public void setAlbumImages_Should_Succeed() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(SetAlbumImagesResult);

        String[] imageIds = new String[2];
        imageIds[0] = "uZdYud1";
        imageIds[1] = "G632tx0";

        client.setAlbumImages("", mAlbumDeleteHash, imageIds, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(SetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(SetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void setAlbumImages_Should_Succeed_Image_Not_Found() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(SetAlbumImagesResult);

        String[] imageIds = new String[1];
        imageIds[0] = "xxxx";

        client.setAlbumImages("", mAlbumDeleteHash, imageIds, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(SetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(SetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAlbumImages_Should_Fail_Exception_No_AlbumId_And_Deletehash_Given() throws InterruptedException {
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(SetAlbumImagesResult);
        client.setAlbumImages("", "", new String[0], cb);
    }

    @Test
    public void addImagesToAlbum_Should_Succeed() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(AddAlbumImagesResult);

        String[] imageIds = new String[2];
        imageIds[0] = "uZdYud1";
        imageIds[1] = "G632tx0";

        client.addImagesToAlbum("", mAlbumDeleteHash, imageIds, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(AddAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(AddAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void addAlbumImages_Should_Succeed_Image_Not_Found() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(AddAlbumImagesResult);

        String[] imageIds = new String[1];
        imageIds[0] = "xxxx";

        client.setAlbumImages("", mAlbumDeleteHash, imageIds, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(AddAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(AddAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addImagesToAlbum_Should_Fail_Exception_No_AlbumId_And_Deletehash_Given()
            throws InterruptedException {
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(AddAlbumImagesResult);
        client.addImagesToAlbum("", "", new String[0], cb);
    }

    @Test
    public void deleteImagesFromAlbum_Should_Succeed() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(DeleteAlbumImagesResult);

        String[] imageIds = new String[2];
        imageIds[0] = "uZdYud1";
        imageIds[1] = "G632tx0";

        client.deleteImagesFromAlbum("", mAlbumDeleteHash, imageIds, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(DeleteAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(DeleteAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void deleteImagesFromAlbum_Should_Succeed_Image_Not_Found() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) createAlbum_Should_Succeed();
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(DeleteAlbumImagesResult);

        String[] imageIds = new String[1];
        imageIds[0] = "xxxx";

        client.deleteImagesFromAlbum("", mAlbumDeleteHash, imageIds, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(DeleteAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(DeleteAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteImagesFromAlbum_Should_Fail_Exception_No_AlbumId_And_Deletehash_Given()
            throws InterruptedException {
        MyCallback<ImgurResponse> cb
                = new MyCallback<>(DeleteAlbumResult);
        client.deleteImagesFromAlbum("", "", new String[0], cb);
    }

    private class AlbumCallback extends MyCallback<ImgurResponse<AlbumResponse>> {

        public AlbumCallback(Result result) {
            super(result);
        }

        @Override
        public void success(ImgurResponse<AlbumResponse> r, Response response) {
            super.success(r, response);
            mAlbumDeleteHash = r.data.deletehash;
        }
    }

    private class MyCallback<T> implements Callback<T> {

        public Result result;

        public MyCallback(Result result) {
            this.result = result;
        }

        @Override
        public void success(T t, Response response) {
            System.out.println("Success");
            result.SUCCESS_HAS_BEEN_CALLED = true;
            result.FAILURE_HAS_BEEN_CALLED = false;
        }

        @Override
        public void failure(RetrofitError error) {
            result.FAILURE_HAS_BEEN_CALLED = true;
            result.SUCCESS_HAS_BEEN_CALLED = false;
            System.out.println("Failure:" + error.toString());
        }
    }

    private class Result {
        public boolean SUCCESS_HAS_BEEN_CALLED;
        public boolean FAILURE_HAS_BEEN_CALLED;
    }
}