package com.nst.facture.billing.controllers.auth;


import com.nst.facture.billing.models.User;
import com.nst.facture.billing.payload.Dto.UserDto;
import com.nst.facture.billing.payload.response.ApiResponse;
import com.nst.facture.billing.service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8081")
@Api("User Controller API")
/**
 * This class describes a userController
 */
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  UserService userService;


    /**
     * This function displays the list of users
     * @return
     */
    @GetMapping
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    /**
     * This function for get a user
     * @param id
     * @return
     */
    @GetMapping("/{id}")
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
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        // convert DTO to entity
        User userRequest = modelMapper.map(userDto, User.class);

        User user = userService.createUser(userRequest);

        // convert entity to DTO
        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO

    /**
     * This function about update a user
     * @param id
     * @param userDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {

        // convert DTO to Entity
        User userRequest = modelMapper.map(userDto, User.class);

        User user = userService.updateUser(id, userRequest);

        // entity to DTO
        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok().body(userResponse);
    }

    /**
     * This function about delete a user
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "User deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


    /*   public ResponseEntity<?> demo(){
        try {
            return new ResponseEntity<>()
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage())
        }
    }*/


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