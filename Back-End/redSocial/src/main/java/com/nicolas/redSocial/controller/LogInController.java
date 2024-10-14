package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.service.UserService;
import com.nicolas.redSocial.webtoken.JwtService;
import com.nicolas.redSocial.webtoken.LogInForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LogInController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public String autenticateAndGetToken(@RequestBody LogInForm logInForm) {
        Authentication res = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInForm.getUsername(), logInForm.getPassword()));

        if (res.isAuthenticated()) {
            return jwtService.generateToken(userService.loadUserByUsername(logInForm.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }

}
