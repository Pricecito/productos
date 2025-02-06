package com.api.productos.service;

import com.api.productos.model.Usuarios;
import com.api.productos.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UsuariosService {
    @Autowired
    private UsuariosRepository repository;

    public List<Usuarios> findAll() {
        return repository.findAll();
    }

    public Optional<Usuarios> findById(UUID id) {
        return repository.findById(id);
    }

    public Usuarios save(Usuarios usuario) {
        return repository.save(usuario);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
