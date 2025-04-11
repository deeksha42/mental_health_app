package com.example.mentalhealth.service;

import com.example.mentalhealth.model.User;
import com.example.mentalhealth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Signup: Save user if not exists
    public boolean registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false; // User already exists
        }
        userRepository.save(new User(username, password));
        return true;
    }

    // Login: Check credentials
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
