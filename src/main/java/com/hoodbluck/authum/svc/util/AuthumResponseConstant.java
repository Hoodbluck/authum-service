package com.hoodbluck.authum.svc.util;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public interface AuthumResponseConstant {
    /**
     * This is a status sent when the received deviceToken is wrong.
     * Code will be -1.
     */
    String STATUS_DEVICE_TOKEN_INVALID = "device_token_invalid";
    /**
     * This is a status sent when the received userId is wrong.
     * Code will be -1.
     */
    String STATUS_USER_INVALID_ID = "user_invalid_id";
    /**
     * This is a status sent when the user for registering is invalid.
     */
    String STATUS_REGISTRATION_INVALID = "user_invalid";
    /**
     * This is a status sent for a user registration that is already
     * registered. Code will be -1.
     */
    String STATUS_REGISTRATION_DUPLICATED = "user_already_registered";
    /**
     * This is a status sent for invalid login. Code will be -1.
     */
    String STATUS_LOGIN_INVALID = "login_invalid";
    /**
     * This is a status sent for server errors. Code will be -1.
     */
    String STATUS_SERVER_ERROR = "server_error";
    /**
     * This is a status sent for default success actions. Code will be 1.
     */
    String STATUS_SUCCESS = "success";
    /**
     * This is a status code to represent a successful process.
     */
    int CODE_SUCCESS = 0;
    /**
     * This is a status code to represent a failed process.
     */
    int CODE_FAILURE = -1;
    /**
     * This is a status code to represent a successful process that had some warnings.
     */
    int CODE_WARNING = 1;
}
