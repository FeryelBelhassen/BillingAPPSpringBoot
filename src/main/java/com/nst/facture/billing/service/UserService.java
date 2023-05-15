package com.nst.facture.billing.service;

import com.nst.facture.billing.models.User;
import com.nst.facture.billing.payload.Dto.UserDto;

import java.util.List;

/**
 * This interface of user service
 */
public interface UserService  {
    List<User> getAllUsers();

    User addUserFromDTO(UserDto userDto);
    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    User getUserByUsername(String username);


}