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
    
    //Busca un usuario por su id y si existe lo devuleve
    @GetMapping("/user/info/{id}")
    public User getUserInfoById(@PathVariable int id){
    
        return userService.getUserById(id);
        
    }
    
    //Busca un usuario por su nombre y si existe lo devuleve
    @GetMapping("/user/info/{username}")
    public User getUserInfoByUsername(@PathVariable String username){
    
        return userService.getUserByUername(username);
        
    }
    
    //Busca un usuario por su mail y si existe lo devuleve
    @GetMapping("/user/info/{mail}")
    public User getuserInfoByMail(@PathVariable String mail){
    
        return userService.getUserByMail(mail);
        
    }
    
    
    @GetMapping("/admin")
    public String admin(){
        return "Niverl de administrador";
    }
}
