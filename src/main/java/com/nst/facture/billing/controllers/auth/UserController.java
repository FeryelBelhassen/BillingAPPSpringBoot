package com.nst.facture.billing.controllers;


import com.nst.facture.billing.models.User;
import com.nst.facture.billing.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    @Autowired
    private UserService userService;
// JAVADOC
    private List<User> users = createList();


 /*   public ResponseEntity<?> demo(){
        try {
            return new ResponseEntity<>()
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage())
        }
    }*/
    private static List<User> createList() {
        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("Feryel");
        user1.setEmail("feryel@gmail.com");
        user1.setPassword("123456789");
        //user1.setTelephone(52369874);
        //user1.setRoles("ROLE_ADMIN");
        User user2 = new User();
        user2.setUsername("Ahmed");
        user2.setEmail("ahmed@gmail.com");
        user2.setPassword("ahmed12345");
       // user2.setTelephone(20132654);


        userList.add(user1);
        userList.add(user2);

        return userList;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        System.out.println("Added User - " + user.getUsername());
        users.add(user);
        return user;
    }


    @PutMapping("/users/{username}")
    public User updateUser(@PathVariable(value = "username") String username, @RequestBody User userDetails) {
        System.out.println("Updated User - " + username);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(users.indexOf(user));
                users.add(userDetails);
                break;
            }
        }
        return userDetails;
    }

    @DeleteMapping("/users/{username}")
    public User deleteUser(@PathVariable(value = "username") String username) {
        System.out.println("Deleted User - " + username);
        User deletedUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);
                deletedUser = user;
                break;
            }
        }
        return deletedUser;
    }


       /* @PostMapping("/save")
        public User saveUser(@RequestBody User user) {

            return userService.saveUser(user);
        }

        @PutMapping("/update")
        public User updateUser(@RequestBody User user) {
            return userService.updateUser(user);
        }*/

/*
        @GetMapping("/getone/{id}")
        public User getUser(@PathVariable(name = "userId") Long id) {
            return userService.getUser(id);
        }

        @DeleteMapping("/delete/{id}")
        public void deleteUser(@PathVariable(name = "id") Long id) {
            userService.deleteUser(id);
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