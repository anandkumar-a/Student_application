package com.demo.studentapp.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.demo.studentapp.entity.User;
import com.demo.studentapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        Objects.requireNonNull(user, "user must not be null");
        return repository.save(user);
    }

    public User login(String username, String password) {
        Objects.requireNonNull(username, "username must not be null");
        Objects.requireNonNull(password, "password must not be null");

        User user = repository.findByUsername(username).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

}