package com.nicolas.redSocial.service;

import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //Carga el usuario de la base de datos para iniciar la sesion
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getUsername())
                    .password(user.get().getPassword()) //-> se tiene que oculatar la contraseña
                    .roles(getRole(user.get()))
                    .build();

        } else {
            throw new UsernameNotFoundException("El usuario no existe en la base de datos");
        }

    }

    //Determnia los roles del usuario
    public String[] getRole(User user) {

        if (user.getRole() == null) {
            return new String[]{"USER"};
        } else {
            return user.getRole().split(",");
        }
    }

    //Busca un usuario por su id y si existe lo devuleve
    public User getUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {

            return user.get();

        } else {
            throw new UsernameNotFoundException("El usuario no existe en la base de datos");
        }
    }

    //Busca un usuario por su nombre y si existe lo devuleve
    public User getUserByUername(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {

            return user.get();

        } else {
            throw new UsernameNotFoundException("El usuario no existe en la base de datos");
        }

    }

    //Busca un usuario por su nombre y si existe lo devuleve
    public User getUserByMail(String mail) {

        Optional<User> user = userRepository.findByMail(mail);
        if (user.isPresent()) {

            return user.get();

        } else {
            throw new UsernameNotFoundException("El usuario no existe en la base de datos");
        }

    }

    //Modificar cuenta:
    //Cambio de la biografia
    public User updateUserBio(int id, String bio) {

        User user = getUserById(id);
        user.setBio(bio);
        return userRepository.save(user);

    }

    //Cambio de la foto de perfil
    public User updateUserPicture(int id, String newUrl) {

        User user = getUserById(id);
        user.setProfilePicture(newUrl);
        return userRepository.save(user);

    }

    //Cambio del nombre de usuario
    public User updateUsername(int id, String newName) {

        User user = getUserById(id);
        user.setUsername(newName);
        return userRepository.save(user); //debe ser unico, puede fallar

    }

    //Cambio del mail
    public User updateUserMail(int id, String mail) {

        User user = getUserById(id);
        user.setMail(mail);
        return userRepository.save(user);

    }

    //Borra un usuario de la base de datos
    public void deleteAcount(int id) {

        userRepository.deleteById(id);

    }

}
