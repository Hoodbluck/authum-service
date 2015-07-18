package com.hoodbluck.authum.svc.model;

/**
 * Authum Response Model.
 * Created on 7/18/15.
 *
 * @see <a href="https://github.com/Hoodbluck/doc/wiki/Models#authumresponse">Authum's Model Page</a>
 * @author Adrian Pena
 */
public class AuthumResponse {
    private int code;
    private String status;
    private String value;

    public AuthumResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
