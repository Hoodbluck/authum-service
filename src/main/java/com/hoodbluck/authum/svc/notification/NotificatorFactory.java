package com.hoodbluck.authum.svc.notification;

import com.hoodbluck.authum.svc.util.StringHelper;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class NotificatorFactory {
    public static final String USER_AGENT_ANDROID = "com.hoodbluck.android";
    public static final String USER_AGENT_IOS = "com.hoodbluck.ios";

    private NotificatorFactory() {}

    public static Notificator getNotificator(String userAgent) {
        if(StringHelper.equals(userAgent, USER_AGENT_ANDROID)) {
            return new GoogleNotificator();
        }
        //not checking user agent ios because the swift network library (AlamoFire) has an issue with headers.
        return new AppleNotificator();
    }
}
