package com.goit.service;

import com.goit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(String username, String password) {
        if (isValidUsername(username) && isValidPassword(password)) {
            String hashedPassword = hashPassword(password);
            return userService.createUser(username, hashedPassword);
        }
        return null;
    }

    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPasswordHash());
    }

    private boolean isValidUsername(String username) {
        return username != null &&
                username.matches("^[a-zA-Z0-9]{5,50}$");
    }

    private boolean isValidPassword(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.length() <= 100;
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User authenticate(String username, String password) {
        User user = userService.findByUsername(username);
        if (user != null && isValidPassword(password) && checkPassword(user, password)) {
            return user;
        }
        return null;
    }
}