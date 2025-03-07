package com.api.productos.controller;

import com.api.productos.dto.ProductosDTO;
import com.api.productos.model.Productos;
import com.api.productos.model.Usuarios;
import com.api.productos.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductosService service;

    @GetMapping
    public ResponseEntity<List<ProductosDTO>> findAll(){
        List<Productos> listProductos = service.findAll();
        if(listProductos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<ProductosDTO> listDTO=listProductos.stream().map(producto -> ProductosDTO.builder()
                .id(producto.getId().toString())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(producto.getCategoria().getNombre())
                .build()).toList();
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDTO> findById(@PathVariable("id") UUID id){
        var producto = service.findById(id).get();
        if(Objects.isNull(producto)){
            return ResponseEntity.notFound().build();
        }
        ProductosDTO productoDTO=ProductosDTO.builder()
                .id(producto.getId().toString())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
        return ResponseEntity.ok(productoDTO);
    }

    @PostMapping
    public ResponseEntity<Productos> save(@RequestBody Productos producto){
        URI location= URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(producto.getId()).toUriString());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Productos> update(@RequestBody Productos producto){
        Productos p = service.findById(producto.getId()).get();
        if(Objects.nonNull(p)){
            service.save(p);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<Productos> updatePartial(@RequestBody Productos producto){
        Productos p = service.findById(producto.getId()).get();
        if(Objects.nonNull(p)){
            if(Objects.nonNull(producto.getNombre()))p.setNombre(producto.getNombre());
            if(Objects.nonNull(producto.getDescripcion()))p.setDescripcion(producto.getDescripcion());
            if(Objects.nonNull(producto.getPrecio()))p.setPrecio(producto.getPrecio());
            if(Objects.nonNull(producto.getStock()))p.setStock(producto.getStock());
            service.save(p);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Productos> delete(@PathVariable("id") UUID id){
        Productos p = service.findById(id).get();
        if(Objects.nonNull(p)){
            service.deleteById(p.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
