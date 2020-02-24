package com.netcracker.service;

import com.netcracker.model.Author;
import com.netcracker.model.User;
import com.netcracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public List<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> retrieveByLogin(String login) {
        return userRepository.retrieveByLogin(login);
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
