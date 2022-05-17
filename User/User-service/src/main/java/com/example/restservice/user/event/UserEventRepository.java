package com.example.restservice.user.event;

import com.example.restservice.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public UserEventRepository(@Value("${service.reservations.url}") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(User user) {
        restTemplate.delete("/users/{id}", user.getId());
    }

    public void create(User user) {
        User user1 = new User(user.getLogin(),user.getEmail());
        HttpEntity<User> request = new HttpEntity<>(user1);
        restTemplate.postForLocation("/users", request);
    }
}
