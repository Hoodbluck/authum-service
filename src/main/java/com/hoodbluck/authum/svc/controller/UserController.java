package com.hoodbluck.authum.svc.controller;

import com.hoodbluck.authum.svc.manager.UserManager;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public AuthumResponse register(@RequestBody String user) {
        return null;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public AuthumResponse login(@RequestParam("user") String user,
                                @RequestParam("password") String password) {
        return null;
    }
}
