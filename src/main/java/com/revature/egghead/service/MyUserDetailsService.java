package com.revature.egghead.service;

import com.revature.egghead.model.User;
import com.revature.egghead.repository.UserRepository;
import com.revature.egghead.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null) throw new UsernameNotFoundException(username);
        return new MyUserPrincipal(user);
    }

    public void test(){
    }
}
