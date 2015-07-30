package com.akiniyalocts.imgur_api;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.enums.AlbumLayout;
import com.akiniyalocts.imgur_api.model.post.AlbumResponse;

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
public class RealApiTest {

    private ImgurClient client = ImgurClient.getInstance("3efbb6d75f6524f");
    private Result GetAlbumInfoResult = new Result();
    private Result GetAlbumImagesResult = new Result();
    private Result GetAlbumImageResult = new Result();
    private Result CreateAlbumResult = new Result();
    private Result UpdateAlbumResult = new Result();
    private Result DeleteAlbumResult = new Result();

    private static final int TIMEOUT = 3500;
    public String mAlbumDeleteHash = "";

    @Test
    public void testAlbumShouldBeGetAndSerialized() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<Album>> cb
                = new MyCallback<>(GetAlbumInfoResult);

        client.getAlbumInfo("VZtsj", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumInfoResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumInfoResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    public void testAlbumShouldBeFound() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<Album>> cb
                = new MyCallback<>(GetAlbumInfoResult);

        client.getAlbumInfo("xxxxxxx", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumInfoResult.SUCCESS_HAS_BEEN_CALLED, false);
        assertEquals(GetAlbumInfoResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    @Test
    public void testAlbumImagesShouldBeLoaded() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<List<Image>>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImages("VZtsj", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    public void testAlbumImagesShouldNotBeLoaded() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<List<Image>>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImages("xxxxxxxx", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, false);
        assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    @Test
    public void testAlbumImageShouldBeLoaded() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<Image>> cb
                = new MyCallback<>(GetAlbumImageResult);

        client.getAlbumImage("VZtsj", "VpXOjOE", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(GetAlbumImageResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumImageResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    @Ignore
    public void testAlbumImageShouldNotBeLoaded() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<Image>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImage("VZtsj", "xxxxx", cb);

        Thread.sleep(TIMEOUT);

        //this is wrong! But the api returns status code 200 and success!
        assertEquals(GetAlbumImageResult.SUCCESS_HAS_BEEN_CALLED, true);
        //assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    @Test
    public void testAlbumShouldBeCreated() throws InterruptedException {
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
    public void testAlbumShouldBeUpdated() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) testAlbumShouldBeCreated();
        com.akiniyalocts.imgur_api.model.post.Album album = new com.akiniyalocts.imgur_api.model.post.Album();
        album.setTitle("some title2");
        album.setDescription("some description2");
        album.setLayout(AlbumLayout.BLOG);

        MyCallback<com.akiniyalocts.imgur_api.model.Response> cb
                = new MyCallback<>(UpdateAlbumResult);

        client.updateAlbum(album, "", mAlbumDeleteHash, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(UpdateAlbumResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(UpdateAlbumResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void testAlbumShouldNotBeUpdated() throws InterruptedException {
        com.akiniyalocts.imgur_api.model.post.Album album = new com.akiniyalocts.imgur_api.model.post.Album();
        album.setTitle("some title2");

        MyCallback<com.akiniyalocts.imgur_api.model.Response> cb
                = new MyCallback<>(UpdateAlbumResult);

        client.updateAlbum(album, "", "blablabla", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(UpdateAlbumResult.FAILURE_HAS_BEEN_CALLED, true);
        assertEquals(UpdateAlbumResult.SUCCESS_HAS_BEEN_CALLED, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAlbumShouldNotBeUpdatedException() {
        com.akiniyalocts.imgur_api.model.post.Album album = new com.akiniyalocts.imgur_api.model.post.Album();

        MyCallback<com.akiniyalocts.imgur_api.model.Response> cb
                = new MyCallback<>(new Result());

        client.updateAlbum(album, "", "", cb);
    }

    @Test
    public void testAlbumShouldBeDeleted() throws InterruptedException {
        //Your test is bad and you should feel bad.
        if (mAlbumDeleteHash.isEmpty()) testAlbumShouldBeCreated();

        MyCallback<com.akiniyalocts.imgur_api.model.Response> cb
                = new MyCallback<>(DeleteAlbumResult);

        client.deleteAlbum("", mAlbumDeleteHash, cb);

        Thread.sleep(TIMEOUT);

        assertEquals(DeleteAlbumResult.FAILURE_HAS_BEEN_CALLED, false);
        assertEquals(DeleteAlbumResult.SUCCESS_HAS_BEEN_CALLED, true);
    }

    @Test
    public void testAlbumShouldNotBeDeleted() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response> cb
                = new MyCallback<>(DeleteAlbumResult);

        client.deleteAlbum("", "blablabla", cb);

        Thread.sleep(TIMEOUT);

        assertEquals(DeleteAlbumResult.FAILURE_HAS_BEEN_CALLED, true);
        assertEquals(DeleteAlbumResult.SUCCESS_HAS_BEEN_CALLED, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAlbumShouldNotBeDeletedException() {
        MyCallback<com.akiniyalocts.imgur_api.model.Response> cb
                = new MyCallback<>(new Result());

        client.deleteAlbum(null, "", cb);
    }

    private class AlbumCallback extends MyCallback<com.akiniyalocts.imgur_api.model.Response<AlbumResponse>> {

        public AlbumCallback(Result result) {
            super(result);
        }

        @Override
        public void success(com.akiniyalocts.imgur_api.model.Response<AlbumResponse> r, Response response) {
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