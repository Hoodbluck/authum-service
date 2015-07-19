package com.hoodbluck.authum.svc.notification.model;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
public class APNSPayload {
    private APS aps;

    public APNSPayload() {
    }

    public APNSPayload(APS aps) {
        this.aps = aps;
    }

    public APS getAps() {
        return aps;
    }

    public void setAps(APS aps) {
        this.aps = aps;
    }
}
