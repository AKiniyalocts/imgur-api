package com.akiniyalocts.imgur_api;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Image;

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
    public Result GetAlbumInfoResult = new Result();
    private Result GetAlbumImagesResult = new Result();

    @Test
    public void testAlbumShouldBeGetAndSerialized() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<Album>> cb
                = new MyCallback<>(GetAlbumInfoResult);

        client.getAlbumInfo("VZtsj", cb);

        Thread.sleep(3000);

        assertEquals(GetAlbumInfoResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumInfoResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    public void testAlbumShouldBeFound() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<Album>> cb
                = new MyCallback<>(GetAlbumInfoResult);

        client.getAlbumInfo("xxxxxxx", cb);

        Thread.sleep(3000);

        assertEquals(GetAlbumInfoResult.SUCCESS_HAS_BEEN_CALLED, false);
        assertEquals(GetAlbumInfoResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    @Test
    public void testAlbumImagesShouldBeLoaded() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<List<Image>>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImages("VZtsj", cb);

        Thread.sleep(3000);

        assertEquals(GetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, false);
    }

    @Test
    public void testAlbumImagesShouldNotBeLoaded() throws InterruptedException {
        MyCallback<com.akiniyalocts.imgur_api.model.Response<List<Image>>> cb
                = new MyCallback<>(GetAlbumImagesResult);

        client.getAlbumImages("xxxxxxxx", cb);

        Thread.sleep(3000);

        assertEquals(GetAlbumImagesResult.SUCCESS_HAS_BEEN_CALLED, false);
        assertEquals(GetAlbumImagesResult.FAILURE_HAS_BEEN_CALLED, true);
    }

    private class MyCallback<T> implements Callback<T> {

        public Result result;

        public MyCallback(Result result) {
            this.result = result;
        }

        @Override
        public void success(T t, Response response) {
            result.SUCCESS_HAS_BEEN_CALLED = true;
            result.FAILURE_HAS_BEEN_CALLED = false;
        }

        @Override
        public void failure(RetrofitError error) {
            result.FAILURE_HAS_BEEN_CALLED = true;
            result.SUCCESS_HAS_BEEN_CALLED = false;
        }
    }

    private class Result {
        public boolean SUCCESS_HAS_BEEN_CALLED;
        public boolean FAILURE_HAS_BEEN_CALLED;
    }
}
