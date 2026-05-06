package com.sanosysalvos.ms_geo.repositories;

import com.sanosysalvos.ms_geo.models.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
}