package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationControler {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
            
    @PostMapping("/register/user")
    public User createNewUser(@RequestBody User user){
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
        
    }

}
