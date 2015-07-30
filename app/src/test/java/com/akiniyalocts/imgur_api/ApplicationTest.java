package com.akiniyalocts.imgur_api;

import com.akiniyalocts.imgur_api.model.Album;
import com.akiniyalocts.imgur_api.model.Response;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit.Callback;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Mock
    private ImgurAPI mockApi;

    @Captor
    private ArgumentCaptor<Callback<Response<Album>>> cb;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore //fuck this shit :)
    public void testShouldCallSuccessOnCallbackWithAlbum() throws Exception {
        Mockito.verify(mockApi).getAlbumInfo(Mockito.anyString(), cb.capture());
        String fu = Mockito.anyString();
        Response<Album> response = new Response<>();
        Album album = Mockito.mock(Album.class, Answers.RETURNS_DEEP_STUBS);
        response.data = album;
        mockApi.getAlbumInfo(fu, cb.capture());
        verify(mockApi).getAlbumInfo(fu, cb.capture());
        cb.getValue().success(response, null);

        assertEquals(album.getId().isEmpty(), false);

        verify(album).getId().contains(fu);
    }
}