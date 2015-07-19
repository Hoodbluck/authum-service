package com.hoodbluck.authum.svc.authentication;

import com.hoodbluck.authum.svc.exception.TimeOutException;
import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class AuthorizationRequestListener {
    private static final int LISTEN_TIME_OUT_SECONDS = 25;

    private User mUser;
    private Client mClient;
    private volatile boolean triggered = false;
    private volatile boolean authenticated = false;

    public AuthorizationRequestListener(Client client, User user) {
        this.mUser = user;
        this.mClient = client;
    }

    public User getUser() {
        return mUser;
    }

    public Client getClient() {
        return mClient;
    }

    public void handleResponse(boolean authenticated) {
        this.authenticated = authenticated;
        this.triggered = true;
    }

    public boolean listenAndBlock() throws TimeOutException {
        long initialTime = System.currentTimeMillis();
        while(!triggered) {
            long currentTime = System.currentTimeMillis();
            long durationInSeconds = (currentTime - initialTime) / 1000;

            if(durationInSeconds >= LISTEN_TIME_OUT_SECONDS) {
                //time out.
                throw new TimeOutException();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                //NOP
            }
        }
        return authenticated;
    }
}
