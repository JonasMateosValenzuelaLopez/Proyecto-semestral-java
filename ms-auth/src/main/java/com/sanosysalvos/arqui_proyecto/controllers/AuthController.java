package com.sanosysalvos.arqui_proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanosysalvos.arqui_proyecto.dto.LoginRequestDto;
import com.sanosysalvos.arqui_proyecto.dto.TokenResponseDto;
import com.sanosysalvos.arqui_proyecto.services.AuthService;

@RestController 
@RequestMapping("/api/auth") 
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request) {
        
        TokenResponseDto response = authService.login(request);
        
        return ResponseEntity.ok(response); 
    }
}