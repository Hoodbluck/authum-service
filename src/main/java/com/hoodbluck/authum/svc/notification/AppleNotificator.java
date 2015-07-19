package com.hoodbluck.authum.svc.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.notification.model.APNSPayload;
import com.hoodbluck.authum.svc.notification.model.APS;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.Payload;
import javapns.notification.PushedNotifications;
import org.json.JSONException;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class AppleNotificator implements Notificator {
    private static final String PASSPHRASE = "Ihatecertificates01";
    private static final String KEY_STORE = "authumAPNSCertificate.p12";
    private static final String APNS_PAYLOAD_CATEGORY = "AUTHENTICATE_CATEGORY";

    AppleNotificator() {}

    @Override
    public void notifyUser(User user, Client client, String message) {
        try {
            APNSPayload apnsPayload = new APNSPayload(new APS(message, APNS_PAYLOAD_CATEGORY), client.getClientId());
            String payloadValue = new ObjectMapper().writeValueAsString(apnsPayload);
            Payload payload = new Payload(payloadValue){};
            PushedNotifications response = Push.payload(payload, KEY_STORE, PASSPHRASE, false, user.getDeviceToken());
            response.size();
        } catch (CommunicationException e) {
            System.out.println(e.getMessage());
        } catch (KeystoreException e) {
            System.out.println(e.getMessage());
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}
