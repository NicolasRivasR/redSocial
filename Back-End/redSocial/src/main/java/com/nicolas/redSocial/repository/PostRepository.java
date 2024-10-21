
package com.nicolas.redSocial.repository;

import com.nicolas.redSocial.models.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {
  
    //puede que hagan falta mas finciones
    Optional<List<Post>>  findByUserId( int userid);
    
}
