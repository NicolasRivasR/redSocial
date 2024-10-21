package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.DAOs.PostDao;
import com.nicolas.redSocial.models.Post;
import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.service.PostService;
import com.nicolas.redSocial.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
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
    
    @Autowired
    private UserService userService;

    @GetMapping("/post")
    public List<PostDao> getAllPost() {
        System.out.println("Voy al repositorio");
        return postService.getAllPost().stream().map(post ->  postToPostDao(post)).collect(Collectors.toList());
        
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

        Post newPost = postService.crearPostParaUsuario(userId, post);

        return ResponseEntity.ok(newPost);

    }

    @PostMapping("/updatePost/{userId}/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable int userId, @PathVariable int postId) {
        Post updatedPost;
        try {
            updatedPost = postService.updatePost(userId, postId);
        } catch (Exception e) {

            return (ResponseEntity<Post>) ResponseEntity.notFound();

        }

        return ResponseEntity.ok(updatedPost);

    }
    
    @GetMapping("/posts/{username}")
    public ResponseEntity<List<PostDao>> getPostsFromUser(@PathVariable String username){
        User u;
        try {
            u = userService.getUserByUername(username);
        } catch (Exception e) {
            throw e;
        }
        
        List<Post> posts = postService.getPostsFromUser(u.getId());
        
        return ResponseEntity.ok(posts.stream().map(post ->  postToPostDao(post)).collect(Collectors.toList()));
     
    }

    private PostDao postToPostDao(Post post) {

        return new PostDao(post.getId(), post.getContent(), post.getVideoUrl(), post.getImageUrl(), post.getCreatedAt(), post.getUpdatedAt(), post.getUser().getUsername());

    }
}
