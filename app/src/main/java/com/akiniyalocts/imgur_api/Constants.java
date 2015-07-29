package com.akiniyalocts.imgur_api;

/**
 * Holds constant information about the imgur api like version and base url
 */
public class Constants {
    public static final int API_VERSION = 3;
    private static final String BASE_URL = "https://api.imgur.com";
    public static final String API_BASE_URL = String.format("%s/%d/", BASE_URL, API_VERSION);
    public static final String API_OAUTH_URL = String.format("%s/oauth2/authorize?client_id=", BASE_URL);

    public static final String IMGUR_REDIRECT_URL = "http://android";

    public static final String IMGUR_GRANT_TYPE = "refresh_token";

    public static final String AUTH_BEARER = "Bearer ";

    public static final String AUTH_CLIENT = "Client-ID ";

    public static String CLIENT_ID;

    public static String getClientId() {
        return CLIENT_ID;
    }

    public static void setClientId(String clientId) {
        CLIENT_ID = clientId;
    }

    public static String getGeneratedAuthURL() {
        return Constants.API_OAUTH_URL + Constants.getClientId() + "&response_type=token";
    }

    public static String getBearerAuth() {
        return null;
    }

}
