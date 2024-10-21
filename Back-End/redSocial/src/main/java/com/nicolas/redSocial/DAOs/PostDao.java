
package com.nicolas.redSocial.DAOs;

import java.time.LocalDateTime;


public class PostDao {
    
    

    private int id;
    
    private String content;
    

    private String videoUrl;
    

    private String imageUrl;
    

    private LocalDateTime createdAt;
    

    private LocalDateTime updatedAt;
    

    private String userName;

    public PostDao(int id, String content, String videoUrl, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt, String userName) {
        this.id = id;
        this.content = content;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
}
