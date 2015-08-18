package com.akiniyalocts.imgur_api.realapi;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TestCallback<T> implements Callback<T> {

    public Result result;

    public TestCallback(Result result) {
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