package com.sanosysalvos.arqui_proyecto.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_STRING = "ClaveSecretaSanosySalvos2026JAVA";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());
    private static final long EXPIRATION_TIME = 3600000;

    public String generateToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }
}