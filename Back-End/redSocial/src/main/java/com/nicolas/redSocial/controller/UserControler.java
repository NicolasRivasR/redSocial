package com.nicolas.redSocial.controller;

import com.nicolas.redSocial.DAOs.UserDao;
import com.nicolas.redSocial.models.User;
import com.nicolas.redSocial.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public List<User> hola() {

        return userService.getAllUSers();
    }

    @GetMapping("/user")
    public String user() {
        return "Niverl de usuario basico";
    }

    //Busca un usuario por su id y si existe lo devuleve
    @GetMapping("/user/info/id/{id}")
    public User getUserInfoById(@PathVariable int id) {

        return userService.getUserById(id);

    }

    //Busca un usuario por su nombre y si existe lo devuleve
    @GetMapping("/user/info/name/{username}")
    public ResponseEntity<UserDao> getUserInfoByUsername(@PathVariable String username) throws UsernameNotFoundException {
        User user;
        try {
           user = userService.getUserByUername(username);
        } catch (Exception e) {
                return (ResponseEntity<UserDao>) ResponseEntity.notFound();
        }
            
        return ResponseEntity.ok(userToUserDao(user));
        

    }

    //Busca un usuario por su mail y si existe lo devuleve
    @GetMapping("/user/info/mail/{mail}")
    public User getuserInfoByMail(@PathVariable String mail) {

        return userService.getUserByMail(mail);

    }

    @PostMapping("/user/update/username/{userId}/{newName}")
    public User updateUserame(@PathVariable int userId, @PathVariable String newName) {

        return userService.updateUsername(userId, newName);

    }

    @PostMapping("/user/update/mail/{userId}/{newMail}")
    public User updateMail(@PathVariable int userId, @PathVariable String newMail) {

        return userService.updateUserMail(userId, newMail);

    }

    @PostMapping("/user/update/bio/{userId}/{newBio}")
    public User updateBio(@PathVariable int userId, @PathVariable String newBio) {

        return userService.updateUserBio(userId, newBio);

    }

    @PostMapping("/user/update/foto/{userId}/{newUrl}")
    public User updateProfileFoto(@PathVariable int userId, @PathVariable String newUrl) {

        return userService.updateUserPicture(userId, newUrl);

    }

    @DeleteMapping("/delete/user/{id}")
    public void deleteUser(@PathVariable int userId) {

        userService.deleteAcount(userId);
    }

    @GetMapping("/admin")
    public String admin() {
        return "Niverl de administrador";
    }
    
    private UserDao userToUserDao(User user){
    
        return new UserDao(user.getUsername(), user.getMail(), user.getFirstName(), user.getSecondName(), user.getBio() , user.getProfilePicture());
        
    }
}
