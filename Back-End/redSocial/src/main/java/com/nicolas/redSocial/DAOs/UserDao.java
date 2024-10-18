
package com.nicolas.redSocial.DAOs;


public class UserDao {
    
    private String username;
    
    private String mail;
    
    private String firstName;
    
    private String secondName;
    
    private String bio;
    
    private String profilePicture;

    public UserDao() {
    }
    
    

    public UserDao(String username, String mail, String firstName, String secondName, String bio, String profilePicture) {
        this.username = username;
        this.mail = mail;
        this.firstName = firstName;
        this.secondName = secondName;
        this.bio = bio;
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    
}
