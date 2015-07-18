package com.hoodbluck.authum.svc.dataprovider;

import com.hoodbluck.authum.svc.dataprovider.data.UserRepository;
import com.hoodbluck.authum.svc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 7/18/15.
 *
 * @author Adrian Pena
 */
@Component
public class UserDataProvider {

    @Autowired
    UserRepository mUserProvider;

    public User registerUser(User user) {
        return user;
    }
}
