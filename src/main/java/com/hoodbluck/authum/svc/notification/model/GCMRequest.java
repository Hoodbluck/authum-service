package com.hoodbluck.authum.svc.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Generic GCM Request Body model for data transportation.
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class GCMRequest<T> {

    @JsonProperty("registration_ids")
    private final List<String> registrationIds;

    @JsonProperty("data")
    private final GCMRequestData<T> data;

    public GCMRequest(List<String> registrationIds, String contentType, T data) {
        GCMRequestData<T> requestData = new GCMRequestData<T>();
        requestData.setData(data);
        requestData.setContentType(contentType);

        this.registrationIds = registrationIds;
        this.data = requestData;
    }

    public List<String> getRegistrationIds() {
        return registrationIds;
    }

    public GCMRequestData<T> getData() {
        return data;
    }
}