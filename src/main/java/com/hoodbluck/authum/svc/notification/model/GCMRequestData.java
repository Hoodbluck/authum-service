package com.hoodbluck.authum.svc.notification.model;

/**
 * Generic GCM Request Body Data model for data transportation.
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class GCMRequestData<T> {
    private String contentType;
    private T data;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}