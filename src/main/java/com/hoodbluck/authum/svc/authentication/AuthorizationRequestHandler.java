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
        for(AuthorizationRequestListener registeredListeners : mRequestListeners) {
            if(StringHelper.equals(listener.getUser().getEmail(), registeredListeners.getUser().getEmail())
                    && StringHelper.equals(listener.getClient().getClientId(), registeredListeners.getClient().getClientId())) {
                System.out.println("A listener for " + listener.getUser().getUserId() + " @ " + listener.getClient().getClientId() + " is already registered, ignoring...");
                return;
            }
        }
        System.out.println("A listener for " + listener.getUser().getUserId() + " @ " + listener.getClient().getClientId() + " got registered.");
        mRequestListeners.add(listener);
    }

    public void notifyAuthenticationResponse(User user, Client client, boolean authenticated) {
        System.out.println("Notification received for: " + user.getUserId() + " @ " + client.getClientId() + " starting to look for a listener...");
        for(AuthorizationRequestListener listener : mRequestListeners) {
            if (StringHelper.equals(user.getEmail(), listener.getUser().getEmail())
                    && StringHelper.equals(client.getClientId(), listener.getClient().getClientId())) {
                System.out.println("Found a listener for: " + user.getUserId() + " @ " + client.getClientId() + " starting to handle response...");
                listener.handleResponse(authenticated);
                System.out.println("Handled authentication response for " + user.getUserId() + " @ " + client.getClientId());
                mRequestListeners.remove(listener);
                return;
            }
        }
    }
}
