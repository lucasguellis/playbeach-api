package com.pbio.playbeach.entities.dto;

public class SubscribeUserRequestDTO {
    String userEmail;
    Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getUserEmail() {
        return userEmail;
    }
}