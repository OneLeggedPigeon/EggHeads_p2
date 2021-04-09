package com.revature.egghead.repository;

import com.revature.egghead.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    
}
