package com.hoodbluck.authum.svc.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoodbluck.authum.svc.dataprovider.UserDataProvider;
import com.hoodbluck.authum.svc.exception.AuthumException;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.util.AuthumResponseConstant;
import com.hoodbluck.authum.svc.util.ResponseUtil;
import com.hoodbluck.authum.svc.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
@Component
public class UserManager {

    @Autowired
    UserDataProvider mUserDataProvider;

    /**
     * Registers a user in the Authum service.
     * @param user the user to register.
     * @return the AuthumResponse for registration.
     */
    public AuthumResponse registerUser(User user) {
        try {
            validateUser(user);
        } catch (AuthumException e) {
            return ResponseUtil.createFailureResponse(e.getStatus(), e.getValue());
        }
        if(mUserDataProvider.getUser(user.getEmail()) != null) {
            return ResponseUtil.createFailureResponse(AuthumResponseConstant.STATUS_REGISTRATION_DUPLICATED, "This email is already registered");
        }
        user = mUserDataProvider.saveUser(user);
        try {
            String userContent = new ObjectMapper().writeValueAsString(user);
            return ResponseUtil.createSuccessResponse(userContent);
        } catch (JsonProcessingException e) {
            return ResponseUtil.createFailureResponse(e.getMessage());
        }
    }

    /**
     * Validates user credentials against the Authum's service.
     * @param email the user's email.
     * @param password the user's password.
     * @return an AuthumResponse for login.
     */
    public AuthumResponse login(String email, String password) {
        User user = mUserDataProvider.getUser(email);
        if(user != null && StringHelper.equals(password, user.getPassword())) {
            try {
                String userContent = new ObjectMapper().writeValueAsString(user);
                return ResponseUtil.createSuccessResponse(userContent);
            } catch (JsonProcessingException e) {
                return ResponseUtil.createFailureResponse(e.getMessage());
            }
        }
        return ResponseUtil.createFailureResponse(AuthumResponseConstant.STATUS_LOGIN_INVALID, "The user's email or password is wrong");
    }

    /**
     * Updates the given user (through the received userId) device token.
     * @param userId the user's id.
     * @param deviceToken the user's device token
     * @return an AuthumResponse for updating the user's device token.
     */
    public AuthumResponse updateUserDeviceToken(int userId, String deviceToken) {
        if(StringUtils.isEmpty(deviceToken)) {
            return ResponseUtil.createFailureResponse(AuthumResponseConstant.STATUS_DEVICE_TOKEN_INVALID, "The device token is invalid.");
        }
        User user = mUserDataProvider.getUser(userId);
        if(user != null) {
            user.setDeviceToken(deviceToken);
            mUserDataProvider.saveUser(user);
            return ResponseUtil.createSuccessResponse("The user device token is updated.");
        }
        return ResponseUtil.createFailureResponse(AuthumResponseConstant.STATUS_USER_INVALID_ID, "The user id is invalid.");
    }

    /**
     * Validates the given user.
     * @param user the user to validate.
     * @throws AuthumException if the user is invalid.
     */
    private void validateUser(User user) throws AuthumException {
        String errorValue = "";
        if(user != null) {
            if(StringHelper.isEmpty(user.getEmail())
                    || StringHelper.isEmpty(user.getPassword())) {
                errorValue = "The user credentials are empty.";
            }

            if(StringHelper.isEmpty(user.getFirstName())
                    || StringHelper.isEmpty(user.getLastName())) {
                errorValue = "The user's information is empty.";
            }
        } else {
            errorValue = "The user object is empty.";
        }

        if(StringHelper.isNotEmpty(errorValue)) {
            throw new AuthumException(AuthumResponseConstant.STATUS_REGISTRATION_INVALID, errorValue);
        }
    }
}
