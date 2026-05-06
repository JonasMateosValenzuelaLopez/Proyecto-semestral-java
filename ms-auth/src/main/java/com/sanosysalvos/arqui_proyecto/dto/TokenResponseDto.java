package com.sanosysalvos.arqui_proyecto.dto;

public class TokenResponseDto {
    
    private String token;

    // Constructor
    public TokenResponseDto(String token) {
        this.token = token;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}