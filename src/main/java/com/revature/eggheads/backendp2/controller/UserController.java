package com.revature.eggheads.backendp2.controller;

import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository repo;

    @GetMapping
    public @ResponseBody
    List<User> getUsers(){return repo.findAll();}

    @PostMapping
    public @ResponseBody User save(@RequestBody User u){return repo.save(u);}
}
