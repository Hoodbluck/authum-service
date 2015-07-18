package com.hoodbluck.authum.svc.exception;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class AuthumException extends Exception {
    private String status;
    private String value;

    public AuthumException(String status, String value) {
        super(value);
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }
}
