package com.hoodbluck.authum.svc.model;

/**
 * User's model.
 * Created on 7/18/15.
 *
 * @see <a href="https://github.com/Hoodbluck/doc/wiki/Models#user">Authum's Model Guide</a>
 * @author Adrian Pena
 */
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String deviceToken;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
