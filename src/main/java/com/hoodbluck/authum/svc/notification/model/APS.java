package com.hoodbluck.authum.svc.notification.model;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
public class APS {
    private String alert;
    private String category;

    public APS(String alert, String category) {
        this.alert = alert;
        this.category = category;
    }

    public APS() {
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
