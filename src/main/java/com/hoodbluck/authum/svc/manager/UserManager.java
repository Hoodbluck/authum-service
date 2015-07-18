package com.hoodbluck.authum.svc.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoodbluck.authum.svc.dataprovider.UserDataProvider;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.util.AuthumResponseConstant;
import com.hoodbluck.authum.svc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        if(mUserDataProvider.getUser(user.getEmail()) != null) {
            return ResponseUtil.createFailureResponse(AuthumResponseConstant.STATUS_REGISTRATION_DUPLICATED);
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
        if(user != null) {
            try {
                String userContent = new ObjectMapper().writeValueAsString(user);
                return ResponseUtil.createSuccessResponse(userContent);
            } catch (JsonProcessingException e) {
                return ResponseUtil.createFailureResponse(e.getMessage());
            }
        }
        return ResponseUtil.createFailureResponse(AuthumResponseConstant.STATUS_LOGIN_INVALID);
    }
}
