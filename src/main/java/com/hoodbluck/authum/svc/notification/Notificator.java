package com.hoodbluck.authum.svc.notification;

import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public interface Notificator {
    void notifyUser(User user, Client client, String message);
}
