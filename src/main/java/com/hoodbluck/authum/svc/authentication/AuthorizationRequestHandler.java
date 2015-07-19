package com.hoodbluck.authum.svc.authentication;

import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.util.StringHelper;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class AuthorizationRequestHandler {
    private static CopyOnWriteArrayList<AuthorizationRequestListener> mRequestListeners
            = new CopyOnWriteArrayList<AuthorizationRequestListener>();

    public void registerListener(AuthorizationRequestListener listener) {
        mRequestListeners.add(listener);
    }

    public void notifyAuthenticationResponse(User user, Client client, boolean authenticated) {
        for (AuthorizationRequestListener listener : mRequestListeners) {
            if (StringHelper.equals(user.getEmail(), listener.getUser().getEmail())
                    && StringHelper.equals(client.getClientId(), listener.getClient().getClientId())) {
                listener.handleResponse(authenticated);
            }
        }
    }
}
