package com.akiniyalocts.imgur_api.model;

/**
 * Created by anthony on 7/26/15.
 */
public class Basic {
    protected Object data;
    protected boolean success;
    protected int status;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
