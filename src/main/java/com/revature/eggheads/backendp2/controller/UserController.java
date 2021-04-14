package com.revature.eggheads.backendp2.controller;

import com.revature.eggheads.backendp2.authentication.AuthenticationRequest;
import com.revature.eggheads.backendp2.authentication.AuthenticationResponse;
import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.repository.UserRepository;
import com.revature.eggheads.backendp2.service.MyUserDetailsService;
import com.revature.eggheads.backendp2.util.JwtUtil;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    UserRepository repo;
    private AuthenticationManager authenticationManager;
    private MyUserDetailsService userDetailsService;
    private JwtUtil jwtTokenUtil;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository repo, AuthenticationManager am, MyUserDetailsService muds, JwtUtil jwtu, PasswordEncoder pe ){
        this.repo = repo;
        this.authenticationManager = am;
        this.userDetailsService = muds;
        this.jwtTokenUtil = jwtu;
        this.passwordEncoder = pe;
    }

    @GetMapping
    public @ResponseBody
    List<User> getUsers(){return repo.findAll();}

    @GetMapping("/{id}")
    public @ResponseBody
    User getUser( @PathVariable("id") String id){
        return repo.findById(Integer.valueOf(id)).orElse(null);
    }


    @PostMapping("/create")
    public @ResponseBody User newUser(@RequestBody User u){
        User user = new User();
        user.setUsername(u.getUsername());
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        return repo.save(user);
    }


    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<HttpStatus> delete(@PathVariable("id") String id){
        repo.findById(Integer.valueOf(id)).ifPresent(
                u-> repo.delete(u)
        );
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch( BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
