package com.chat.service;

import com.chat.model.User;
import com.chat.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void removeUser(User user) {
       userRepository.delete(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}
