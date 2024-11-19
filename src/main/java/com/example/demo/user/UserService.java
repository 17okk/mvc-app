package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public boolean registerUser(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null)
            return false;

        User newUser = new User();

        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPassword(password);
        userRepository.save(newUser);

        return true;
    }

    public List<User> getUsersByMajor(String major) {
        return userRepository.findUsersByMajor(major);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findUsersByNameMatching(name);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}
