package com.nst.facture.billing.service;

import com.nst.facture.billing.models.User;
import com.nst.facture.billing.payload.Dto.UserDto;
import com.nst.facture.billing.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {

        return (List<User>) userRepository.findAll();
    }
    @Override
    public User addUserFromDTO(UserDto userDto){
        User toAdd = new User();
        BeanUtils.copyProperties(userDto, toAdd);
        return userRepository.save(toAdd);
    }
    @Override
    public User updateUser(Long id, User user) {
        User userDB =getUserById(id);
        userDB.setUsername(user.getUsername());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        userDB.setRoles(user.getRoles());
        User updatedBook = getUserById(id);

        return updatedBook;
    }



    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            userRepository.delete(u);

        });
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            // Handle the case when the user with the given ID is not found
            throw new ResourceNotFoundException("User not found for ID: " + id);
        }
    }


}

