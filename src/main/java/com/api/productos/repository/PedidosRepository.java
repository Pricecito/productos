package com.api.productos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.productos.model.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, UUID> {

}
