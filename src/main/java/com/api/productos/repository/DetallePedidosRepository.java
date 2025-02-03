package com.api.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.productos.model.DetallePedidos;

@Repository
public interface DetallePedidosRepository extends JpaRepository<DetallePedidos, Long> {

}
