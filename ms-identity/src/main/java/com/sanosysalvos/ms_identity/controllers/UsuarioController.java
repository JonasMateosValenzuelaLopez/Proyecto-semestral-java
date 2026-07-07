package com.sanosysalvos.ms_identity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sanosysalvos.ms_identity.dto.UsuarioRequestDto;
import com.sanosysalvos.ms_identity.models.Usuario;
import com.sanosysalvos.ms_identity.services.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody UsuarioRequestDto request) {
        try {
            Usuario creado = usuarioService.registrarUsuario(request);
            return ResponseEntity.ok(creado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/test-protegido")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Si puedes leer esto, es porque tu Token es válido.");
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody UsuarioRequestDto dto) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}