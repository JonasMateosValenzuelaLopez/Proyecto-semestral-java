package com.sanosysalvos.arqui_proyecto.dto;

public class TokenResponse {

    private String token;

    // Constructor vacío
    public TokenResponse() {
    }

    public TokenResponse(String token) {
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