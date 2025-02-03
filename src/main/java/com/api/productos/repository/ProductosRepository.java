package com.api.productos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.productos.model.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, UUID> {

}
