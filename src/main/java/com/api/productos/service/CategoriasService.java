package com.api.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.productos.model.Categorias;
import com.api.productos.repository.CategoriasRepository;

@Service
@Transactional
public class CategoriasService {
    @Autowired
    private CategoriasRepository repository;

    public List<Categorias> findAll() {
        return repository.findAll();
    }

    public Optional<Categorias> findById(Long id) {
        return repository.findById(id);
    }

    public Categorias save(Categorias categoria) {
        return repository.save(categoria);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
