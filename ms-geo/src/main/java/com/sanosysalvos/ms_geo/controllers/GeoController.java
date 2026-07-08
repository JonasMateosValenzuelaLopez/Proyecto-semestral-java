package com.sanosysalvos.ms_geo.controllers;

import com.sanosysalvos.ms_geo.models.Ubicacion;
import com.sanosysalvos.ms_geo.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geo")
public class GeoController {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @GetMapping("/ubicaciones")
    public List<Ubicacion> obtenerUbicaciones() {
        return ubicacionRepository.findAll();
    }
}