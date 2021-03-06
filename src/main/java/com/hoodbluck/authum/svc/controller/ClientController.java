package com.hoodbluck.authum.svc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoodbluck.authum.svc.manager.ClientManager;
import com.hoodbluck.authum.svc.model.AuthumResponse;
import com.hoodbluck.authum.svc.model.Client;
import com.hoodbluck.authum.svc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientManager mClientManager;

    @RequestMapping(
            method = RequestMethod.POST
    )
    public AuthumResponse registerClient(@RequestBody String clientContent) {
        try {
            Client client = new ObjectMapper().readValue(clientContent, Client.class);
            return mClientManager.saveClient(client);

        } catch (IOException e) {
            return ResponseUtil.createServerErrorResponse(e.getMessage());
        }
    }

    @RequestMapping(
            value = "/{clientId}/user/{userId}/auth",
            method = RequestMethod.GET
    )
    public ResponseEntity<AuthumResponse> requestAuthorizationByUserId(@PathVariable("clientId") String clientId, @PathVariable("userId") int userId) {
        AuthumResponse body = mClientManager.requestAuthorization(clientId, userId);
        List<String> value = new ArrayList<String>();
        value.add("*");
        LinkedMultiValueMap<String,String> headers = new LinkedMultiValueMap<String, String>();
        headers.put("Access-Control-Allow-Origin", value);
        return new ResponseEntity<AuthumResponse>(body, headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{clientId}/user/email/{userEmail}/auth",
            method = RequestMethod.GET
    )
    public AuthumResponse requestAuthorizationByUserEmail(@PathVariable("clientId") String clientId, @PathVariable("userEmail") String userEmail) {
        return mClientManager.requestAuthorization(clientId, userEmail);
    }
}
