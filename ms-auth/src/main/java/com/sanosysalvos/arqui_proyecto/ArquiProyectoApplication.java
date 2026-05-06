package com.sanosysalvos.arqui_proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sanosysalvos.arqui_proyecto.models.Credencial;
import com.sanosysalvos.arqui_proyecto.repositories.CredencialRepository;

@SpringBootApplication
public class ArquiProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArquiProyectoApplication.class, args);
	}

	// Este "Bean" se ejecuta automáticamente al iniciar la aplicación
	@Bean
	public CommandLineRunner initData(CredencialRepository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Verificamos si el usuario ya existe para no duplicarlo cada vez que reinicies
			if (repository.findByCorreo("admin@sanosysalvos.cl").isEmpty()) {
				Credencial admin = new Credencial();
				admin.setCorreo("admin@sanosysalvos.cl");
				
				// ¡Aquí está la magia! Encriptamos la clave "123456" antes de guardarla en Oracle
				admin.setContrasena(passwordEncoder.encode("123456")); 
				
				repository.save(admin);
				System.out.println("✅ Usuario de prueba creado en PostgreSQL con éxito.");
			}
		};
	}
}