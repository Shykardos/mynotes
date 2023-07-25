package com.goit.service;

import com.goit.repository.UserRepository;
import com.goit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(String username, String hashedPassword) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(hashedPassword);
        return saveUser(newUser);
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String hash(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(User user, String password) {
        String hashedPassword = hash(password);
        return user.getPasswordHash().equals(hashedPassword);
    }
}