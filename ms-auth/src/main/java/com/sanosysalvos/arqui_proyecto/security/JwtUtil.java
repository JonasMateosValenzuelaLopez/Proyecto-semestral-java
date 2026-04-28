package com.sanosysalvos.arqui_proyecto.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Genera una llave secreta ultra segura automáticamente
    // (En un entorno de producción real, esto se saca de las variables de entorno)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // El token durará 1 hora (tiempo en milisegundos)
    private static final long EXPIRATION_TIME = 3600000;

    public String generateToken(String correo) {
        return Jwts.builder()
                .setSubject(correo) // El dato principal que guardamos en el token
                .setIssuedAt(new Date()) // Fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de muerte
                .signWith(SECRET_KEY) // Firmado con nuestra llave secreta
                .compact(); // Construye el String final
    }
}