package com.api.productos;

import com.api.productos.model.Usuarios;
import com.api.productos.service.UsuariosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductosApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UsuariosService service) {
		return args -> {
			Usuarios user1 = new Usuarios().builder()
					.nombre("Matias")
					.correo("matias.gutierrez@gmail.com")
					.contraseña("123456")
					.build();
			service.save(user1);
		};
	}




}
