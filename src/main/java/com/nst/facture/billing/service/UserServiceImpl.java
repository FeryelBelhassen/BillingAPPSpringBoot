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
    public User updateUser(User user) {
        User toupdate= new User();
        BeanUtils.copyProperties(user, toupdate);
        return userRepository.save(toupdate);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            userRepository.delete(u);

        });
    }

    /*@Override
    public User deleteAllUsers(){
        return userRepository.delete(u);
    }*/
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}

