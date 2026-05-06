package com.sanosysalvos.ms_identity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sanosysalvos.ms_identity.models.Usuario;
import com.sanosysalvos.ms_identity.repositories.UsuarioRepository;
import com.sanosysalvos.ms_identity.dto.UsuarioRequestDto;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el encriptador

    public Usuario registrarUsuario(UsuarioRequestDto dto) {
        if (usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreCompleto(dto.getNombreCompleto());
        nuevoUsuario.setCorreo(dto.getCorreo());
        nuevoUsuario.setTelefono(dto.getTelefono());
        nuevoUsuario.setComuna(dto.getComuna());
        
        // Encriptamos la contraseña antes de guardar
        nuevoUsuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));

        return usuarioRepository.save(nuevoUsuario);
    }
}