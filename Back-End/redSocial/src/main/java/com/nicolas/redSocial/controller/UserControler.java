package com.nicolas.redSocial.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserControler {
   
    @GetMapping("/home")
    public String hola(){
        return "Hooa";
    }
    
    @GetMapping("/user")
    public String user(){
        return "Niverl de usuario basico";
    }
    
    @GetMapping("/admin")
    public String admin(){
        return "Niverl de administrador";
    }
}
