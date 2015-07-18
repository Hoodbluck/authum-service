package com.hoodbluck.authum.svc.dataprovider.data;

import com.hoodbluck.authum.svc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
