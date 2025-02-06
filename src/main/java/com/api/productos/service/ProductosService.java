package com.api.productos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.productos.model.Productos;
import com.api.productos.repository.ProductosRepository;

@Service
@Transactional
public class ProductosService {

    @Autowired
    private ProductosRepository repository;

    public List<Productos> findAll() {
        return repository.findAll();
    }

    public Optional<Productos> findById(UUID id) {
        return repository.findById(id);
        
    }

    public Productos save(Productos producto) {
        return repository.save(producto);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
