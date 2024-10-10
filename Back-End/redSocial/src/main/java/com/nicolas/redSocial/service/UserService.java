package com.nicolas.redSocial.service;

import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {

            return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getUserName())
                .password(user.get().getPassword()) //-> se tiene que oculatar la contraseña
                .roles(getRole(user.get()))
                .build();
         
        } else {
            throw new UsernameNotFoundException("El usuario no existe en la base de datos");
        }

    }
    
    public String[] getRole(User user){
    
        if(user.getRole() == null){
            return new String[]{"USER"} ;
        }
        else{
            return user.getRole().split(",");
        }
    };

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("Estamos probando cosas"); 
        if(!username.equals("user")) {throw new UsernameNotFoundException("No estas");}
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));
        
        return new User( 1L,"user", passwordEncoder.encode("pasword") );
    }*/
}
