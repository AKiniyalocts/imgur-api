package com.akiniyalocts.imgur_api.realapi;

import com.akiniyalocts.imgur_api.ImgurClient;
import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.ImgurResponse;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Well, some "real life" testing so we dont need an activity which executes the actions every time.
 */
public class MemeApiTest {

    private static ImgurClient client;
    private Result GetDefaultMemeResult = new Result();

    @BeforeClass
    public static void init() {
        ImgurClient.initialize(Constants.API_KEY);
        client = ImgurClient.getInstance();
    }

    @Test
    public void GetAlbumInfo_Should_Succeed_Album_Exists() throws InterruptedException {
        TestCallback<ImgurResponse<List<Image>>> cb
                = new TestCallback<>(GetDefaultMemeResult);

        client.getDefaultMemes(cb);

        Thread.sleep(Constants.TIMEOUT);

        assertEquals(GetDefaultMemeResult.SUCCESS_HAS_BEEN_CALLED, true);
        assertEquals(GetDefaultMemeResult.FAILURE_HAS_BEEN_CALLED, false);
    }
}