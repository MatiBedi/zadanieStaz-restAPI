package com.example.restservice.configuration;


import com.example.restservice.user.model.User;
import com.example.restservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final UserService userService;

    @Autowired
    public InitializedData(UserService userService){
        this.userService = userService;
    }

    @PostConstruct
    private synchronized void init(){

    }
}
