package com.hoodbluck.authum.svc.notification.model;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
public class APS {
    private String alert;
    private String category;
    private String sound;

    public APS(String alert, String category, String sound) {
        this.alert = alert;
        this.category = category;
        this.sound = sound;
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

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
