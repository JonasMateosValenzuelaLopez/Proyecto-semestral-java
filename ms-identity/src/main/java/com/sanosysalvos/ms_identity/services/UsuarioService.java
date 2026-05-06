package com.sanosysalvos.ms_identity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sanosysalvos.ms_identity.models.Usuario;
import com.sanosysalvos.ms_identity.repositories.UsuarioRepository;
import com.sanosysalvos.ms_identity.dto.UsuarioRequestDto;
import java.util.List;

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

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // ACTUALIZAR
    public Usuario actualizarUsuario(Long id, UsuarioRequestDto dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Actualizamos los campos permitidos
        usuarioExistente.setNombreCompleto(dto.getNombreCompleto());
        usuarioExistente.setTelefono(dto.getTelefono());
        usuarioExistente.setComuna(dto.getComuna());
        
        // Si el DTO trae una nueva contraseña, la encriptamos
        if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
            usuarioExistente.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // ELIMINAR
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}