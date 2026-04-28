package com.sanosysalvos.arqui_proyecto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1. Aquí definimos que usaremos BCrypt para encriptar las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Aquí configuramos las reglas de los "guardias de seguridad"
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactivamos CSRF porque usaremos tokens JWT, no cookies web
            .authorizeHttpRequests(auth -> auth
                // Le decimos que cualquier petición que vaya a /api/auth/... es PÚBLICA (para poder hacer login)
                .requestMatchers("/api/auth/**").permitAll() 
                // Cualquier otra petición a este microservicio requiere estar autenticado
                .anyRequest().authenticated()
            );
            
        return http.build();
    }
}