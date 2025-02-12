package com.api.productos;

import com.api.productos.model.Categorias;
import com.api.productos.model.Productos;
import com.api.productos.model.Usuarios;
import com.api.productos.service.ProductosService;
import com.api.productos.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class ProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductosApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UsuariosService service, ProductosService serviceProductos) {
		return args -> {
			Usuarios user1 = new Usuarios().builder()
					.nombre("Matias")
					.correo("matias.gutierrez@gmail.com")
					.contraseña("123456")
					.build();
			service.save(user1);

			/*INSERTANTO PRODUCTOS Y CATEGORIAS*/
			Categorias categoria1 = new Categorias().builder()
					.nombre("Electrónica")
					.build();
			Categorias categoria2 = new Categorias().builder()
					.nombre("Ropa")
					.build();

			// Crear productos
			Productos producto1 = new Productos().builder()
					.nombre("Televisor")
					.descripcion("Televisor 4K")
					.precio(500.0)
					.stock(10)
					.categoria(categoria1)
					.build();
			Productos producto2 = new Productos().builder()
					.nombre("Smartphone")
					.descripcion("Smartphone Android")
					.precio(300.0)
					.stock(20)
					.categoria(categoria1)
					.build();
			Productos producto3 = new Productos().builder()
					.nombre("Laptop")
					.descripcion("Laptop con 16GB RAM")
					.precio(1000.0)
					.stock(5)
					.categoria(categoria1)
					.build();
			Productos producto4 = new Productos().builder()
					.nombre("Camiseta")
					.descripcion("Camiseta de algodón")
					.precio(20.0)
					.stock(50)
					.categoria(categoria2)
					.build();
			Productos producto5 = new Productos().builder()
					.nombre("Jeans")
					.descripcion("Jeans de mezclilla")
					.precio(40.0)
					.stock(30)
					.categoria(categoria2)
					.build();
			serviceProductos.saveAll(List.of(producto1, producto2, producto3, producto4, producto5));
			log.info("Guardando categorias y productos");
		};
	}




}
