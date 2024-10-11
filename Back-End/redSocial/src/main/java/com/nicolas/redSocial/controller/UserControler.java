package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserControler {
   
    @Autowired
    private UserService userService;
    
    @GetMapping("/home")
    public String hola(){
        return "Hooa";
    }
    
    @GetMapping("/user")
    public String user(){
        return "Niverl de usuario basico";
    }
    
    @GetMapping("/user/info/{id}")
    public User getuserInfo(@PathVariable int id){
    
        return userService.getUserById(id);
        
    }
    
    @GetMapping("/admin")
    public String admin(){
        return "Niverl de administrador";
    }
}
