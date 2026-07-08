package com.sanosysalvos.ms_geo;

import com.sanosysalvos.ms_geo.models.Ubicacion;
import com.sanosysalvos.ms_geo.repositories.UbicacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsGeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGeoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initGeoData(UbicacionRepository repository) {
        return args -> {
            if (repository.count() == 0) {

                repository.save(new Ubicacion("Santiago Centro", "Kilómetro 0 - Plaza de Armas", -33.4372, -70.6506));
                repository.save(new Ubicacion("Providencia", "Sector Costanera Center", -33.4173, -70.6061));
                repository.save(new Ubicacion("Maipú", "Sector Templo Votivo", -33.5111, -70.7525));
                repository.save(new Ubicacion("Puente Alto", "Plaza de Puente Alto", -33.6117, -70.5758));
                repository.save(new Ubicacion("La Florida", "Sector paradero 14", -33.5227, -70.5983));
                repository.save(new Ubicacion("Las Condes", "Sector Escuela Militar", -33.4121, -70.5791));
                repository.save(new Ubicacion("Quilicura", "Sector Industrial Norte", -33.3611, -70.7303));
                repository.save(new Ubicacion("San Bernardo", "Plaza de San Bernardo", -33.5922, -70.7056));


                repository.save(new Ubicacion("Valparaíso", "Plaza Sotomayor - Puerto", -33.0375, -71.6292));
                repository.save(new Ubicacion("Viña del Mar", "Reloj de Flores", -33.0245, -71.5518));
                repository.save(new Ubicacion("Reñaca", "Sector Primer Sector", -32.9734, -71.5451));
                repository.save(new Ubicacion("Concón", "Sector Rotonda Concón", -32.9256, -71.5164));

                repository.save(new Ubicacion("Quilpué", "Sector Plaza Arturo Prat", -33.0489, -71.4422));
                repository.save(new Ubicacion("Villa Alemana", "Sector Paseo Latorre", -33.0436, -71.3719));
                repository.save(new Ubicacion("Limache", "Sector San Francisco", -32.9841, -71.2619));
                repository.save(new Ubicacion("Quillota", "Plaza de Armas Quillota", -32.8804, -71.2464));
                

                repository.save(new Ubicacion("San Felipe", "Valle del Aconcagua Centro", -32.7507, -70.7252));
                repository.save(new Ubicacion("Los Andes", "Sector Plaza de Los Andes", -32.8335, -70.5978));
                repository.save(new Ubicacion("San Antonio", "Sector Puerto San Antonio", -33.5908, -71.6111));

                System.out.println("✅ Base de datos geográfica de Chile (RM y V Región) completada.");
            }
        };
    }
}