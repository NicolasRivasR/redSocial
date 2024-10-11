
package com.nicolas.redSocial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class PostController {
   
    @GetMapping("/post")
    public String basic(){
        return "Estas viendo los posts";
    }
}
