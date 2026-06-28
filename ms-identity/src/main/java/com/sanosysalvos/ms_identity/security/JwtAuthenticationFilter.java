package com.sanosysalvos.ms_identity.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List; // IMPORTANTE: Para que funcione List.of

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtils.validateToken(token)) {
                String username = jwtUtils.extractUsername(token);
                
                // Creamos el permiso (ROLE_USER)
                SimpleGrantedAuthority autoridad = new SimpleGrantedAuthority("ROLE_USER");

                // Creamos la autenticación con el usuario y su permiso
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, 
                        null, 
                        List.of(autoridad)
                );

                // Seteamos la seguridad
                SecurityContextHolder.getContext().setAuthentication(auth);
                
                System.out.println("DEBUG: Token validado con éxito para: " + username);
            }
        }

        filterChain.doFilter(request, response);
    }
}