package com.akiniyalocts.imgur_api.model;

/**
 * Response from api
 * @param T Type is needed when the api supplies data within the basic response
 */
public class ImgurResponse<T> {
    /**
     * The data returned via response
     */
    public T data;

    /**
     * Successful call
     */
    public boolean success;

    /**
     * Status code
     */
    public int status;
}