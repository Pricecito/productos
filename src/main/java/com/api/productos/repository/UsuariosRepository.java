package com.api.productos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.productos.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {

}
