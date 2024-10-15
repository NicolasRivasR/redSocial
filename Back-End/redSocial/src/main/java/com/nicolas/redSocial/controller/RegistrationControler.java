package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RegistrationControler {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
            
    
    /*
    {
        "username": "john_doe1",
        "mail": "johndoe2@example.com",
        "password": "contraseña",
        "firstName": "John",
        "secondName": "Doe",
        "bio": "Software engineer with a passion for technology.",
        "profilePicture": "https://example.com/profile/johndoe.jpg",
        "role": "USER"
    }
    */
    @PostMapping("/register/user")
    public User createNewUser(@RequestBody User user){
        
        System.out.println("Voy a regitrar un nuevo usuario");
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
        //return userRepository.save(user);
        //
        
    }

}
