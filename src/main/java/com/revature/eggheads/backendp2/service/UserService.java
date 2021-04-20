package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User u) { return userRepository.save(u); }

    public void deleteUser(User user) { userRepository.delete(user); }
}
