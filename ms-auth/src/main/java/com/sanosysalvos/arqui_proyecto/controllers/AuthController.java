package com.sanosysalvos.arqui_proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanosysalvos.arqui_proyecto.dto.LoginRequestDto;
import com.sanosysalvos.arqui_proyecto.dto.TokenResponseDto;
import com.sanosysalvos.arqui_proyecto.services.AuthService;

@RestController // Le dice a Spring Boot que este archivo recibe peticiones web
@RequestMapping("/api/auth") // La ruta principal de este controlador
public class AuthController {

    @Autowired
    private AuthService authService;

    // La ruta específica para el login (al sumarse con la de arriba queda /api/auth/login)
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request) {
        
        // Llamamos a tu servicio para que haga la magia y devuelva el token
        TokenResponseDto response = authService.login(request);
        
        return ResponseEntity.ok(response); // Devuelve un código 200 OK con el Token
    }
}