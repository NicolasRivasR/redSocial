package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.service.UserService;
import com.nicolas.redSocial.webtoken.JwtService;
import com.nicolas.redSocial.webtoken.LogInForm;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, String>> autenticateAndGetToken(@RequestBody LogInForm logInForm) {
        System.out.println("Entro en la funcion de autenticar ");
        System.out.println(".--------------------------.");
        Authentication res = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInForm.getUsername(), logInForm.getPassword()));
        System.out.println("Lo hise ");
        System.out.println(".--------------------------.");
        if (res.isAuthenticated()) {
            System.out.println("Todo fino primo");
            System.out.println(".--------------------------.");
            String token = jwtService.generateToken(userService.loadUserByUsername(logInForm.getUsername()));
            System.out.println("Tu token: " + token);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }

}
