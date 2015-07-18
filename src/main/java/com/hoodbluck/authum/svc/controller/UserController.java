package com.hoodbluck.authum.svc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoodbluck.authum.svc.manager.UserManager;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.User;
import com.hoodbluck.authum.svc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @RequestMapping(
            method = RequestMethod.POST
    )
    public AuthumResponse register(@RequestBody String userContent) {
        try {
            //parse structure into object.
            User user = new ObjectMapper().readValue(userContent, User.class);
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
}
