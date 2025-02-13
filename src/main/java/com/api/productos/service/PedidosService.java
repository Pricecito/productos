package com.api.productos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.productos.model.Pedidos;
import com.api.productos.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.productos.model.DetallePedidos;
import com.api.productos.repository.DetallePedidosRepository;

@Service
@Transactional
public class PedidosService {
    @Autowired
    private PedidosRepository repository;

    public List<Pedidos> findAll() {
        return repository.findAll();
    }

    public Optional<Pedidos> findById(UUID id) {
        return repository.findById(id);
    }

    public Pedidos save(Pedidos pedidos) {
        return repository.save(pedidos);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
