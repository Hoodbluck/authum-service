package com.hoodbluck.authum.svc.controller;

import com.hoodbluck.authum.svc.model.AuthumResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
@RestController("/user")
public class UserController {

    @RequestMapping(
            method = RequestMethod.POST
    )
    public AuthumResponse register(@RequestBody String user) {
        return null;
    }
}
