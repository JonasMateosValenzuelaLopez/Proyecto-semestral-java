package com.sanosysalvos.ms_mascotas.services;

import com.sanosysalvos.ms_mascotas.models.Mascota;
import com.sanosysalvos.ms_mascotas.repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public Mascota registrarMascota(Mascota mascota) {
        // Por defecto, si no le mandan estado, lo ponemos como PERDIDO
        if (mascota.getEstado() == null || mascota.getEstado().isEmpty()) {
            mascota.setEstado("PERDIDO");
        }
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> listarPorEstado(String estado) {
        return mascotaRepository.findByEstado(estado);
    }
    
    // PUT: Actualizar mascota
    public Mascota actualizarMascota(Long id, Mascota datosNuevos) {
        return mascotaRepository.findById(id).map(mascotaExistente -> {
            mascotaExistente.setNombre(datosNuevos.getNombre());
            mascotaExistente.setEspecie(datosNuevos.getEspecie());
            mascotaExistente.setRaza(datosNuevos.getRaza());
            mascotaExistente.setDescripcion(datosNuevos.getDescripcion());
            mascotaExistente.setEstado(datosNuevos.getEstado());
            return mascotaRepository.save(mascotaExistente);
        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
    }

    // DELETE: Eliminar mascota
    public void eliminarMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Mascota no encontrada");
        }
        mascotaRepository.deleteById(id);
    }
}
