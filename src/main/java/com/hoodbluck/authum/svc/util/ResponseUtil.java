package com.hoodbluck.authum.svc.util;

import com.hoodbluck.authum.svc.model.AuthumResponse;

/**
 * Utility factory-like class to create AuthumRespon objects.
 * Created on 5/17/15.
 *
 * @author Adrian Pena
 */
public class ResponseUtil {

    /**
     * Creates a server error response with code {@link AuthumResponseConstant#STATUS_SERVER_ERROR}.
     * @param value The error value.
     * @return a failure {@link AuthumResponse}
     */
    public static AuthumResponse createServerErrorResponse(String value) {
        return createFailureResponse(AuthumResponseConstant.STATUS_SERVER_ERROR, value);
    }

    /**
     * Creates a failure response with an empty value.
     * @param status An error status.
     * @return a failure {@link AuthumResponse}
     */
    public static AuthumResponse createFailureResponse(String status) {
        return createFailureResponse(status, null);
    }

    /**
     * Creates a failure response with the given code and value.
     * @param status An error status.
     * @param value An error value.
     * @return a failure {@link AuthumResponse}
     */
    public static <T> AuthumResponse createFailureResponse(String status, T value) {
        return new AuthumResponse<T>(AuthumResponseConstant.CODE_FAILURE, status, value);
    }

    /**
     * Create a success response with the {@link AuthumResponseConstant#CODE_SUCCESS} code and no value.
     * @return a success {@link AuthumResponse}
     */
    public static AuthumResponse createSuccessResponse() {
        return createSuccessResponse(null);
    }

    /**
     * Creates a success response with given value.
     * @param value a success value.
     * @return a success {@link AuthumResponse}
     */
    public static <T> AuthumResponse createSuccessResponse(T value) {
        return new AuthumResponse<T>(AuthumResponseConstant.CODE_SUCCESS, AuthumResponseConstant.STATUS_SUCCESS, value);
    }

    /**
     * Creates a warning response with given code and no value.
     * @param status a warning status.
     * @return a warning {@link AuthumResponse}
     */
    public static AuthumResponse createWarningResponse(String status) {
        return createWarningResponse(status, null);
    }

    /**
     * Creates a warning response with given code and value.
     * @param status a warning status.
     * @param value a warning value.
     * @return a warning {@link AuthumResponse}
     */
    public static <T> AuthumResponse createWarningResponse(String status, T value) {
        return new AuthumResponse<T>(AuthumResponseConstant.CODE_WARNING, status, value);
    }
}
