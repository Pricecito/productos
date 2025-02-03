package com.api.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.productos.model.DetallePedidos;
import com.api.productos.repository.DetallePedidosRepository;

@Service
@Transactional
public class DetallePedidosService {

    @Autowired
    private DetallePedidosRepository repository;

    public List<DetallePedidos> findAll() {
        return repository.findAll();
    }

    public Optional<DetallePedidos> findById(Long id) {
        return repository.findById(id);
    }

    public DetallePedidos save(DetallePedidos detallePedido) {
        return repository.save(detallePedido);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
