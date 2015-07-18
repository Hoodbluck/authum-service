package com.hoodbluck.authum.svc.dataprovider;

import com.hoodbluck.authum.svc.dataprovider.data.UserRepository;
import com.hoodbluck.authum.svc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return mUserProvider.save(user);
    }

    /**
     * Gets a user through its email.
     * @param email the user's email
     * @return the found user.
     */
    public User getUser(String email) {
        List<User> users = mUserProvider.findByEmail(email);
        if(users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    /**
     * Gets a user through its id.
     * @param userId the user's id.
     * @return the user if found.
     */
    public User getUser(int userId) {
        return mUserProvider.getOne(userId);
    }

    /**
     * Gets all the stored users
     * @return the list of users.
     */
    public List<User> getUsers() {
        return mUserProvider.findAll();
    }
}
