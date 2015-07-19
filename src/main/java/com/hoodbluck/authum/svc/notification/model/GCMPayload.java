package com.hoodbluck.authum.svc.notification.model;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
public class GCMPayload {
    private String message;
    private String clientId;

    public GCMPayload() {
    }

    public GCMPayload(String message, String clientId) {
        this.message = message;
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
