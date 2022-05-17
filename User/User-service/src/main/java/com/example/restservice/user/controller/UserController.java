package com.example.restservice.user.controller;

import com.example.restservice.user.model.User;
import com.example.restservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        Optional<User> userFound = userService.find(id);
        if(userFound.isPresent()){
            return new ResponseEntity<>(userFound.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.create(new User(user.getLogin(),user.getEmail()));
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable("id") long id,@RequestBody User user){
        Optional<User> userFound = userService.find(id);
        if(userFound.isPresent()){
            User user1 = userFound.get();
            if(user1.getLogin().equals(user.getLogin())) {
                user1.setEmail(user.getEmail());
                return new ResponseEntity<>(userService.update(user1),HttpStatus.OK);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Podany login nie zgadza się");

    }
}
