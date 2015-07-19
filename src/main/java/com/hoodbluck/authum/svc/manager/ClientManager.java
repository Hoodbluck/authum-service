package com.hoodbluck.authum.svc.manager;

import com.hoodbluck.authum.svc.authentication.AuthorizationRequestHandler;
import com.hoodbluck.authum.svc.authentication.AuthorizationRequestListener;
import com.hoodbluck.authum.svc.dataprovider.ClientDataProvider;
import com.hoodbluck.authum.svc.dataprovider.UserDataProvider;
import com.hoodbluck.authum.svc.exception.TimeOutException;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
@Component
public class ClientManager {

    @Autowired
    ClientDataProvider mClientDataProvider;

    @Autowired
    UserDataProvider mUserDataProvider;

    @Autowired
    NotificationManager mNotificationManager;

    public AuthumResponse saveClient(Client client) {
        client = mClientDataProvider.save(client);
        return ResponseUtil.createSuccessResponse(client.getClientId());
    }

    /**
     * Requests authorization for the given user in the specified client.
     * @param userEmail the userId of the requesting for authorization
     * @return an AuthumResponse for the requested authorization.
     */
    public AuthumResponse requestAuthorization(String clientId, String userEmail) {
        User user = mUserDataProvider.getUser(userEmail);
        Client client = mClientDataProvider.getClient(clientId);

        if(client != null && user != null) {
            return requestAuthorization(client, user);
        }
        return ResponseUtil.createFailureResponse("user_not_found");
    }

    /**
     * Requests authorization for the given user in the specified client.
     * @param userId the userId of the requesting for authorization
     * @return an AuthumResponse for the requested authorization.
     */
    public AuthumResponse requestAuthorization(String clientId, int userId) {
        User user = mUserDataProvider.getUser(userId);
        Client client = mClientDataProvider.getClient(clientId);

        if(client != null && user != null) {
            return requestAuthorization(client, user);
        }
        return ResponseUtil.createFailureResponse("user_not_found");
    }

    /**
     * Requests authorization for the given user in the specified client.
     * @param user the user requested to authorize.
     * @return an AuthumResponse for requesting authorization.
     */
    public AuthumResponse requestAuthorization(Client client, User user) {
        AuthorizationRequestListener listener = new AuthorizationRequestListener(client, user);
        AuthorizationRequestHandler handler = new AuthorizationRequestHandler();
        handler.registerListener(listener);
        boolean authorized;
        try {
            mNotificationManager.notifyUser(user, client);
            authorized = listener.listenAndBlock(); //interrupting call
        } catch (TimeOutException e) {
            return ResponseUtil.createFailureResponse("user_time_out");
        }
        if(authorized) {
            return ResponseUtil.createSuccessResponse(user);
        } else {
            return ResponseUtil.createFailureResponse("user_unauthorized");
        }
    }
}
