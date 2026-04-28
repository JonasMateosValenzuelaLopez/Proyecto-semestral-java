package main.java.com.sanosysalvos.arqui_proyecto.controllers;

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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            // Llamamos al cerebro (Service) para que haga todo el trabajo duro
            TokenResponseDto token = authService.login(request);
            
            // Si todo sale bien, respondemos con un estado 200 (OK) y enviamos el Token
            return ResponseEntity.ok(token);
            
        } catch (RuntimeException e) {
            // Si el Service lanza un error (usuario no existe o clave incorrecta), 
            // atrapamos el error y respondemos con un estado 401 (No Autorizado)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}