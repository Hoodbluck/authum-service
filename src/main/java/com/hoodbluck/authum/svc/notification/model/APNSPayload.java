package com.hoodbluck.authum.svc.notification.model;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
public class APNSPayload {
    private APS aps;
    private String clientId;

    public APNSPayload() {
    }

    public APNSPayload(APS aps, String clientId) {
        this.aps = aps;
        this.clientId = clientId;
    }

    public APS getAps() {
        return aps;
    }

    public void setAps(APS aps) {
        this.aps = aps;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
