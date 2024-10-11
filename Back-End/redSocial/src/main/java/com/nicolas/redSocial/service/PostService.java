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
}
