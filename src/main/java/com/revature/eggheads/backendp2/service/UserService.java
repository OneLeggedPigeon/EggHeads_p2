package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User u){ return userRepository.save(u);}
}
