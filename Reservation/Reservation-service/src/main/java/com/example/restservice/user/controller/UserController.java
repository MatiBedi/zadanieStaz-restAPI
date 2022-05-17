package com.example.restservice.user.controller;

import com.example.restservice.user.model.User;
import com.example.restservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController( UserService userService){ this.userService = userService; }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.create(new User(user.getId()));
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
        Optional<User> user = userService.find(id);

        if(user.isPresent()) {
            userService.delete(user.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
