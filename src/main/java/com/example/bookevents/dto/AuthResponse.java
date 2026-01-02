package com.example.bookevents.dto;

public class AuthResponse {

    private String token;
    private String role;
    private String userId;

    public AuthResponse(String token, String role, String userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }
}
