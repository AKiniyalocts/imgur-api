package com.akiniyalocts.imgur_api;

/**
 * Holds constant information about the imgur api like version and base url
 */
public class Constants {
    public static final String API_BASE_URL = "https://api.imgur.com/3/";

    public static final String IMGUR_REDIRECT_URL = "http://android";

    public static final String IMGUR_GRANT_TYPE = "refresh_token";// required for AUTH header
}
