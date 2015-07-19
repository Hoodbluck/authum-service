package com.hoodbluck.authum.svc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoodbluck.authum.svc.manager.NotificationManager;
import com.hoodbluck.authum.svc.manager.UserManager;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.util.ResponseUtil;
import com.hoodbluck.authum.svc.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserManager mUserManager;

    @Autowired
    NotificationManager mNotificationManager;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public AuthumResponse register(@RequestHeader(HttpHeaders.USER_AGENT) String userAgent, @RequestBody String userContent) {
        try {
            //parse structure into object.
            User user = new ObjectMapper().readValue(userContent, User.class);
            user.setUserAgent(userAgent);
            return mUserManager.registerUser(user);
        } catch (IOException e) {
            return ResponseUtil.createFailureResponse(e.getMessage());
        }
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public AuthumResponse login(@RequestParam("user") String user,
                                @RequestParam("password") String password) {
        return mUserManager.login(user, password);
    }

    @RequestMapping(
            value = "/{userId}/deviceToken",
            method = RequestMethod.POST
    )
    public AuthumResponse updateDeviceToken(@PathVariable("userId") int userId,
                                @RequestParam("deviceToken") String deviceToken) {
        return mUserManager.updateUserDeviceToken(userId, deviceToken);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<User> getAll() {
        return mUserManager.getAllUsers();
    }

    @RequestMapping(
            value = "/{userId}/notify",
            method = RequestMethod.GET
    )
    public String notifyUser(@PathVariable("userId") int userId) {
        return mNotificationManager.notifyUser(userId, "This is a test message from Authum!");
    }

    @RequestMapping(
            value = "/{userId}/client/{clientId}/auth/{authorized}",
            method = RequestMethod.GET
    )
    public String respondAuthorizationByUserId(@PathVariable("userId") int userId, @PathVariable("clientId") String clientId, @PathVariable("authorized") String authorized) {
        boolean authorizedB = StringHelper.equals(authorized, "1");
        System.out.println("respondAuthorizationByUserId: " + userId + " clientId " + clientId + " authorized " + authorizedB);
        mUserManager.respondAuthorization(userId, clientId, authorizedB);
        return "responded";
    }

    @RequestMapping(
            value = "/email/{userEmail}/client/{clientId}/auth/{authorized}",
            method = RequestMethod.POST
    )
    public void respondAuthorizationByUserEmail(@PathVariable("userEmail") String userEmail, @PathVariable("clientId") String clientId, @PathVariable("authorized") String authorized) {
        boolean authorizedB = StringHelper.equals(authorized, "1");
        mUserManager.respondAuthorization(userEmail, clientId, authorizedB);
    }
}
