package com.nicolas.redSocial.service;

import com.nicolas.redSocial.models.Post;
import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.repository.PostRepository;
import com.nicolas.redSocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Post crearPostParaUsuario(int userId, Post post) {
        // Buscar el usuario por su ID
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new IllegalArgumentException("Usuario con ID " + userId + " no encontrado."));

        // Crear un nuevo post
        post.setUser(user);

        // Guardar el post en la base de datos
        return postRepository.save(post);
    }
    
    //Actualia un post, con id post id, del usuario con id userId
    public Post updatePost(int userId, int postId) throws Exception{
    
        User user = userRepository.findById(userId)
                .orElseThrow(()
                        -> new IllegalArgumentException("Usuario con ID " + userId + " no encontrado."));
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new IllegalArgumentException("No esxiste el post con id " + postId)); 
        
        if(post.getUser().getId() == user.getId()){
        
            return postRepository.save(post);
            
        }
        else{
            throw new Exception("El posto no pertenece al usuario que lo quiere modificar");
        }
    }
}
