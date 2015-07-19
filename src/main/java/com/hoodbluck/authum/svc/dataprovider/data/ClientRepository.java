package com.hoodbluck.authum.svc.dataprovider.data;

import com.hoodbluck.authum.svc.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 7/19/15.
 *
 * @author Adrian Pena
 */
public interface ClientRepository extends JpaRepository<Client, String> {

}
