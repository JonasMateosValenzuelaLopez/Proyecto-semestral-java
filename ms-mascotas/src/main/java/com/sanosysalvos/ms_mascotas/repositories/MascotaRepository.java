package com.sanosysalvos.ms_mascotas.repositories;

import com.sanosysalvos.ms_mascotas.models.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    // Para buscar solo los "PERDIDOS" o solo los "ENCONTRADOS"
    List<Mascota> findByEstado(String estado);
    
    // Para que un usuario pueda ver todas las mascotas que ha reportado
    List<Mascota> findByUsuarioId(Long usuarioId);
}
