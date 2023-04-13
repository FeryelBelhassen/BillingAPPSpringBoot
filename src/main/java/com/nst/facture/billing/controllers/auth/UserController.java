package com.nst.facture.billing.controllers.auth;


import com.nst.facture.billing.models.User;
import com.nst.facture.billing.payload.Dto.UserDto;
import com.nst.facture.billing.repository.UserRepository;
import com.nst.facture.billing.service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/users")
    public List<User> allUsers(){
        return userService.getAllUsers();

    }

    /**
     * This function for get a user
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);

        // convert entity to DTO
        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok().body(userResponse);
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
     * @param id , userDetails
     * @return
     */

    @PutMapping("updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){

        User u = userService.updateUser(id, user);
        return new ResponseEntity<User>(u, HttpStatus.OK);
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

    @DeleteMapping("/deleteall")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping("/user")
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