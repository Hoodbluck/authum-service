package com.hoodbluck.authum.svc.dataprovider;

import com.hoodbluck.authum.svc.dataprovider.data.ClientRepository;
import com.hoodbluck.authum.svc.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
@Component
public class ClientDataProvider {
    @Autowired
    ClientRepository mClientRepository;

    public Client save(Client client) {
        return mClientRepository.save(client);
    }

    public Client getClient(String clientId) {
        return mClientRepository.findOne(clientId);
    }
}
