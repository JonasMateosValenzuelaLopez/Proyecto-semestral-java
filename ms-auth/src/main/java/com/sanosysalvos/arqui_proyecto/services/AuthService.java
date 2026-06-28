package com.sanosysalvos.arqui_proyecto.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sanosysalvos.arqui_proyecto.dto.LoginRequestDto;
import com.sanosysalvos.arqui_proyecto.dto.TokenResponseDto;
import com.sanosysalvos.arqui_proyecto.models.Credencial;
import com.sanosysalvos.arqui_proyecto.repositories.CredencialRepository;
import com.sanosysalvos.arqui_proyecto.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenResponseDto login(LoginRequestDto request) {
        
        Optional<Credencial> credencialOpt = credencialRepository.findByCorreo(request.getCorreo());

        if (credencialOpt.isPresent()) {
            Credencial credencial = credencialOpt.get();
            if (passwordEncoder.matches(request.getContrasena(), credencial.getContrasena())) {

                String tokenReal = jwtUtil.generateToken(credencial.getCorreo());
                return new TokenResponseDto(tokenReal);
                
            } else {
                throw new RuntimeException("Contraseña incorrecta");
            }
            
        } else {
            throw new RuntimeException("El usuario con este correo no existe");
        }
    }
}