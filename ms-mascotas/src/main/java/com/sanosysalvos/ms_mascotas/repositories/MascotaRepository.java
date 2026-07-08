package com.sanosysalvos.ms_mascotas.repositories;

import com.sanosysalvos.ms_mascotas.models.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    List<Mascota> findByEstado(String estado);
    
    List<Mascota> findByUsuarioId(Long usuarioId);
}
