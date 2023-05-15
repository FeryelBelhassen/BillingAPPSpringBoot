package com.nst.facture.billing.controllers.auth;


import com.nst.facture.billing.exception.ResourceNotFoundException;
import com.nst.facture.billing.models.User;
import com.nst.facture.billing.payload.Dto.UserDto;
import com.nst.facture.billing.repository.UserRepository;
import com.nst.facture.billing.service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController

@CrossOrigin(origins = "http://localhost:8081")
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<User> allUsers(){
        return userService.getAllUsers();

    }

    /**
     * This function for get a user
     * @param id
     * @return
     */

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/info")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserInfo() {
            // Get the current authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the username from the authentication object
        String username = authentication.getName();

        // Call the UserService to retrieve the user information based on the username
        User user = userService.getUserByUsername(username);
     return user;
    }

    /**
     * This function about create a user
     * @param userDto
     * @return
     */

    @PostMapping("/adduser")
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody UserDto userDto) {

        return userService.addUserFromDTO(userDto);
    }


    /**
     * This function about update a user
     * @param id
     * @return
     */

    @PutMapping("updateuser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping("/useer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

    @GetMapping("/agent")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> getModeratorContent() {
        return ResponseEntity.ok("Moderator content goes here");
    }
}