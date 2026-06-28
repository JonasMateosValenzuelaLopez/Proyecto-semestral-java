package com.sanosysalvos.arqui_proyecto.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sanosysalvos.arqui_proyecto.models.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    Optional<Credencial> findByCorreo(String correo);
}