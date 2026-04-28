package com.sanosysalvos.arqui_proyecto.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sanosysalvos.arqui_proyecto.models.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    
    // Magia de Spring Data JPA: Solo con nombrar el método de esta manera, 
    // Spring crea automáticamente la consulta SQL para buscar al usuario por su correo.
    Optional<Credencial> findByCorreo(String correo);
}