package com.api.productos.controller;

import com.api.productos.model.Productos;
import com.api.productos.model.Usuarios;
import com.api.productos.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductosService service;

    @GetMapping
    public ResponseEntity<List<Productos>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> findById(@PathVariable("id") UUID id){
        var producto = service.findById(id).get();
        if(Objects.isNull(producto)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Productos> save(@RequestBody Productos producto){
        return ResponseEntity.ok(service.save(producto));
    }

    @PutMapping
    public ResponseEntity<Productos> update(@RequestBody Productos producto){
        Productos p = service.findById(producto.getId()).get();
        if(Objects.isNull(p)){
            return ResponseEntity.notFound().build();
        }
        service.save(p);
        return ResponseEntity.noContent().build();
    }
}
