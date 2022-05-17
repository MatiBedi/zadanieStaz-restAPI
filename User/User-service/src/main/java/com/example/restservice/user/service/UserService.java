package com.example.restservice.user.service;

import com.example.restservice.user.event.UserEventRepository;
import com.example.restservice.user.model.User;
import com.example.restservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restservice.user.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserEventRepository userEventRepository;
    @Autowired
    public UserService(UserRepository userRepository, UserEventRepository userEventRepository) {
        this.userRepository = userRepository;
        this.userEventRepository = userEventRepository;
    }

    public Optional<User> find(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User create(User user) {
        User tmp = userRepository.save(user);
        userEventRepository.create(user);
        return tmp;
    }

    @Transactional
    public void delete(Long id) {
        User tmp = userRepository.findById(id).get();
        userEventRepository.delete(tmp);
        userRepository.delete(tmp);
    }

    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }
}
