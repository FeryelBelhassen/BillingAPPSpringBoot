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

    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }
    @Override

    public User getUserByUsername(String username) {
            // Implement the logic to fetch the user by username from your data source (e.g., database)
            // This is just a simple example assuming you have a UserRepository

            // Retrieve the user from the UserRepository by username
            Optional<User> optionalUser = userRepository.findByUsername(username);

            // If the user is found, return it; otherwise, handle accordingly (e.g., throw an exception)
            return optionalUser.orElseThrow(() -> new ResourceNotFoundException ("User not found"));
        }
}




