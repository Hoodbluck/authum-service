package com.hoodbluck.authum.svc.manager;

import com.hoodbluck.authum.svc.authentication.AuthorizationRequestHandler;
import com.hoodbluck.authum.svc.dataprovider.ClientDataProvider;
import com.hoodbluck.authum.svc.dataprovider.UserDataProvider;
import com.hoodbluck.authum.svc.exception.AuthumException;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.util.AuthumResponseConstant;
import com.hoodbluck.authum.svc.util.ResponseUtil;
import com.hoodbluck.authum.svc.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
@Component
public class UserManager {

    @Autowired
    UserDataProvider mUserDataProvider;

    @Autowired
    ClientDataProvider mClientDataProvider;

    @Autowired
    NotificationManager mNotificationManager;

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
        return ResponseUtil.createSuccessResponse(user);
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
            return ResponseUtil.createSuccessResponse(user);
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
        if(StringHelper.isEmpty(deviceToken)) {
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

    /**
     * Gets all the users.
     * @return the list of users.
     */
    public List<User> getAllUsers() {
        return mUserDataProvider.getUsers();
    }

    /**
     * Responds an authorization request.
     * @param userEmail the userId of the responding user.
     * @param authorized true if access is authorized
     */
    public void respondAuthorization(String userEmail, String clientId, boolean authorized) {
        User user = mUserDataProvider.getUser(userEmail);
        Client client = mClientDataProvider.getClient(clientId);
        if(user != null && client != null) {
            respondAuthorization(user, client, authorized);
        }
    }

    /**
     * Responds an authorization request.
     * @param userId the userId of the responding user.
     * @param authorized true if access is authorized
     */
    public void respondAuthorization(int userId, String clientId, boolean authorized) {
        User user = mUserDataProvider.getUser(userId);
        Client client = mClientDataProvider.getClient(clientId);
        if(user != null && client != null) {
            respondAuthorization(user, client, authorized);
        }
    }

    /**
     * Responds an authorization request.
     * @param user the responding user.
     * @param authorized true if access is authorized
     */
    public void respondAuthorization(User user, Client client, boolean authorized) {
        AuthorizationRequestHandler handler = new AuthorizationRequestHandler();
        handler.notifyAuthenticationResponse(user, client, authorized);
    }
}
