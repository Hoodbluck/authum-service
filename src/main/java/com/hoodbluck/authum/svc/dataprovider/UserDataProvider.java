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

    /**
     * Saves a user.
     * @param user the user to save
     * @return the saved user.
     */
    public User saveUser(User user) {
        return user;
    }

    /**
     * Gets a user through his email and password.
     * @param email the user's email
     * @param password the user's password
     * @return the found user.
     */
    public User getUser(String email, String password) {
        return null;
    }
}
