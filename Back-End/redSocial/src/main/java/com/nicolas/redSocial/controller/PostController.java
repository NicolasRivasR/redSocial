package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.models.Post;
import com.nicolas.redSocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String basic() {
        return "Estas viendo los posts";
    }

    /*
    {
        "content": "Este es el contenido de mi post.",
        "videoUrl": "https://example.com/video.mp4",
        "imageUrl": "https://example.com/image.jpg"
    }
    */
    
    @PostMapping("/addPost/{userId}")
    public ResponseEntity<Post> addPost(@PathVariable int userId, @RequestBody Post post) {
        System.out.println("Voy a llamar al servicio");
        Post newPost = postService.crearPostParaUsuario(userId, post);
        System.out.println("Ya ta lo del servicio");
        return ResponseEntity.ok(newPost);

    }
}
