package com.nst.facture.billing.controllers.auth;


import com.nst.facture.billing.exception.ResourceNotFoundException;
import com.nst.facture.billing.models.User;
import com.nst.facture.billing.payload.Dto.UserDto;
import com.nst.facture.billing.repository.UserRepository;
import com.nst.facture.billing.service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController

////@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@Api("User Controller API")
/**
 * This class describes a userController
 */
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  UserService userService;
    @Autowired
    private UserRepository userRepository;

    /**
     * This function displays the list of users
     * @return
     */

    @GetMapping("/users")
    public List<User> allUsers(){
        return userService.getAllUsers();

    }

    /**
     * This function for get a user
     * @param id
     * @return
     */

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    /**
     * This function about create a user
     * @param userDto
     * @return
     */

    @PostMapping("/adduser")
    public User createUser(@RequestBody UserDto userDto) {

        return userService.addUserFromDTO(userDto);
    }


    /**
     * This function about update a user
     * @param id
     * @return
     */

    @PutMapping("updateuser/{id}")
    public User updateUser(@PathVariable("id") @NotNull @Min(1) Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRoles(updatedUser.getRoles());
        userRepository.save(user);
        return user;
    }


    /**
     * This function about delete a user
     * @param id
     * @return
     */
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping("/useer")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

    @GetMapping("/agent")
    public ResponseEntity<?> getModeratorContent() {
        return ResponseEntity.ok("Moderator content goes here");
    }
}