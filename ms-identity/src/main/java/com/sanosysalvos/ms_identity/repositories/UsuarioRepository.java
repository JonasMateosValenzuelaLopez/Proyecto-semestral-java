package com.sanosysalvos.ms_identity.repositories; 

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sanosysalvos.ms_identity.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByCorreo(String correo);
}