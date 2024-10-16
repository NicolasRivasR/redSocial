package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.repository.UserRepository;
import com.nicolas.redSocial.service.UserService;
import com.nicolas.redSocial.webtoken.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

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
    public String createNewUser(@RequestBody User user) {

        System.out.println("Voy a regitrar un nuevo usuario");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        System.out.println("Vamo a autenticalno");
        Authentication res = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())); //ESTO ES LO QUE HACE QUE FALLE  

        if (res.isAuthenticated()) {
            System.out.println("Autenticado");
            return jwtService.generateToken(userService.loadUserByUsername(user.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
        //return userRepository.save(user);
        //

    }

}
