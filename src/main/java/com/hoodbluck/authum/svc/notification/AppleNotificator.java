package com.hoodbluck.authum.svc.notification;

import com.hoodbluck.authum.svc.model.User;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class AppleNotificator implements Notificator {
    private static final String PASSPHRASE = "Ihatecertificates01";
    private static final String KEY_STORE = "authumAPNSCertificate.p12";

    AppleNotificator() {}

    @Override
    public void notifyUser(User user, String message) {
        try {
            Push.alert(message, KEY_STORE, PASSPHRASE, false, user.getDeviceToken());
        } catch (CommunicationException e) {
            System.out.println(e.getMessage());
        } catch (KeystoreException e) {
            System.out.println(e.getMessage());
        }
    }
}
