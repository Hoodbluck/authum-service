package com.hoodbluck.authum.svc.model;

/**
 * Authum Response Model.
 * Created on 7/18/15.
 *
 * @see <a href="https://github.com/Hoodbluck/doc/wiki/Models#authumresponse">Authum's Model Page</a>
 * @author Adrian Pena
 */
public class AuthumResponse<T> {
    private int code;
    private String status;
    private T value;

    public AuthumResponse() {
    }

    public AuthumResponse(int code, String status, T value) {
        this.code = code;
        this.status = status;
        this.value = value;
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

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
