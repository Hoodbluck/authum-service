package com.hoodbluck.authum.svc.manager;

import com.hoodbluck.authum.svc.dataprovider.UserDataProvider;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.User;
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
        return null;
    }

    /**
     * Validates user credentials against the Authum's service.
     * @param email the user's email.
     * @param password the user's password.
     * @return an AuthumResponse for login.
     */
    public AuthumResponse login(String email, String password) {
        return null;
    }
}
