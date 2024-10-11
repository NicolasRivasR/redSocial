
package com.nicolas.redSocial.repository;

import com.nicolas.redSocial.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {
  
    //puede que hagan falta mas finciones
    
}
