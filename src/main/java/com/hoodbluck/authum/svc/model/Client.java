package com.hoodbluck.authum.svc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
@Entity
public class Client {
    @Id
    private String clientId;
    private String name;

    public Client() {
    }

    public Client(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
