package com.sanosysalvos.ms_auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sanosysalvos.ms_auth.dto.LoginRequestDto;
import com.sanosysalvos.ms_auth.dto.TokenResponseDto;
import com.sanosysalvos.ms_auth.services.AuthService;


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