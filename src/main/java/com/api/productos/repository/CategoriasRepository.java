package com.api.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.productos.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

}
