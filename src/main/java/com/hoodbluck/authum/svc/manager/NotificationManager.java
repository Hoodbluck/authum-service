package com.hoodbluck.authum.svc.manager;

import com.hoodbluck.authum.svc.dataprovider.UserDataProvider;
import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.notification.Notificator;
import com.hoodbluck.authum.svc.notification.NotificatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
@Component
public class NotificationManager {

    @Autowired
    UserDataProvider mUserDataProvider;

    public String notifyUser(int userId, String message) {
        User user = mUserDataProvider.getUser(userId);
        if(user != null) {
            return notifyUser(user, message);
        }
        return null;
    }

    public String notifyUser(User user, Client client) {
        String messageFormat = "\"%s\" would like to authenticate.";
        return notifyUser(user, String.format(messageFormat, client.getName()));
    }

    public String notifyUser(User user, String message) {
        Notificator notificator = NotificatorFactory.getNotificator(user.getUserAgent());
        if(notificator != null) {
            notificator.notifyUser(user, message);
            return "Notification: " + message;
        }
        return null;
    }
}
