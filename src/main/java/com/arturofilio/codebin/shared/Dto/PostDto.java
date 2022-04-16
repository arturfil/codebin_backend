package com.arturofilio.codebin.shared.Dto;

import java.io.Serializable;
import java.util.Date;

public class PostDto implements Serializable {
    private  static final long serialVersionUID = 1L;
    private long id;
    private String postId;
    private String title;
    private String content;
    private Date expiresAt;
    private Date createdAt;
    private UserDto user;
    private ExposureDto exposure;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public ExposureDto getExposure() {
        return exposure;
    }
    public UserDto getUser() {
        return this.user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
    public void setExposure(ExposureDto exposure) {
        this.exposure = exposure;
    }
}
