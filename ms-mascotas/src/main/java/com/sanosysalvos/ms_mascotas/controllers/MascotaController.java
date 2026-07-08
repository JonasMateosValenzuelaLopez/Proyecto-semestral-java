package com.sanosysalvos.ms_mascotas.controllers;

import com.sanosysalvos.ms_mascotas.models.Mascota;
import com.sanosysalvos.ms_mascotas.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;


    @PostMapping("/registro")
    public ResponseEntity<Mascota> registrar(@RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.registrarMascota(mascota));
    }


    @GetMapping
    public ResponseEntity<List<Mascota>> listarTodas() {
        return ResponseEntity.ok(mascotaService.listarTodas());
    }


    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Mascota>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(mascotaService.listarPorEstado(estado));
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        try {
            Mascota actualizada = mascotaService.actualizarMascota(id, mascota);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            mascotaService.eliminarMascota(id);
            return ResponseEntity.ok("Mascota con ID " + id + " eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
