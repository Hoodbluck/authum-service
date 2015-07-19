package com.hoodbluck.authum.svc.notification;

import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.notification.model.GCMPayload;
import com.hoodbluck.authum.svc.notification.model.GCMRequest;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public class GoogleNotificator implements Notificator {
    private static final String GCM_SEND_URL = "https://android.googleapis.com/gcm/send";
    private static final String GCM_API_KEY = "AIzaSyAs9cuYBp-IVTqf3YJg71u_uMcXbbm2GvI";

    GoogleNotificator() {}

    @Override
    public void notifyUser(User user, Client client, String message) {
        GCMRequest request = createStringGCMRequest(user, client, message);
        pushGCMNotification(request);
    }

    private GCMRequest<GCMPayload> createStringGCMRequest(User user, Client client, String message) {
        List<String> registrationIds = new ArrayList<String>();
        registrationIds.add(user.getDeviceToken());
        return new GCMRequest<GCMPayload>(registrationIds, "payload", new GCMPayload(message, client.getClientId()));
    }

    private void pushGCMNotification(GCMRequest request) {
        RestTemplate template = new RestTemplate();

        List<MediaType> acceptedMedia = new ArrayList<MediaType>();
        acceptedMedia.add(MediaType.APPLICATION_FORM_URLENCODED);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(acceptedMedia);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "key=" + GCM_API_KEY);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        //message converters
        template.getMessageConverters().add(new FormHttpMessageConverter());

        HttpEntity<GCMRequest> requestEntity = new HttpEntity<GCMRequest>(request, httpHeaders);
        try {
            ResponseEntity<String> response = template.exchange(GCM_SEND_URL, HttpMethod.POST, requestEntity, String.class);
        } catch(Exception e) {
            //NOP
        }
    }
}
