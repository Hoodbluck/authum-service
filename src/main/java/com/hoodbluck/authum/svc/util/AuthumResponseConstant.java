package com.hoodbluck.authum.svc.util;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public interface AuthumResponseConstant {
    /**
     * This is a code sent for server errors. Status will be -1.
     */
    String STATUS_SERVER_ERROR = "server_error";
    /**
     * This is a code sent for default success actions. Status will be 1.
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
