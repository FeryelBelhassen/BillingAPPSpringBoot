package com.nst.facture.billing.security.services;

import com.nst.facture.billing.models.User;

import java.util.List;

/**
 * This interface of user service
 */
public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(long id, User user);

    void deleteUser(long id);

    User getUserById(long id);
}