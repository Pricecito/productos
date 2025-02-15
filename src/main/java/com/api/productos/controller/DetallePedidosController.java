package com.api.productos.controller;

import com.api.productos.dto.DetallePedidosDTO;
import com.api.productos.model.DetallePedidos;
import com.api.productos.service.DetallePedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/detallepedidos")
public class DetallePedidosController {
    @Autowired
    private DetallePedidosService service;

    @GetMapping
    public ResponseEntity<List<DetallePedidosDTO>> findAll() {
        List<DetallePedidos> list = service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<DetallePedidosDTO> listDto = list.stream().map(detalles -> DetallePedidosDTO.builder()
                .id(detalles.getId())
                .cantidad(detalles.getCantidad())
                .subtotal(detalles.getSubtotal())
                .producto(detalles.getProducto().getNombre())
                .pedidoId(detalles.getPedido().getId())
                .build()).toList();
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidosDTO> findById(@PathVariable Long id) {
        DetallePedidos detalle = service.findById(id).get();
        DetallePedidosDTO dto = DetallePedidosDTO.builder()
                .id(detalle.getId())
                .cantidad(detalle.getCantidad())
                .subtotal(detalle.getSubtotal())
                .producto(detalle.getProducto().getNombre())
                .pedidoId(detalle.getPedido().getId())
                .build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetallePedidos> create(@RequestBody DetallePedidos dto) {
        DetallePedidos detalle = service.save(dto);
        URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUriString());
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidosDTO> update(@PathVariable Long id, @RequestBody DetallePedidosDTO dto) {
        DetallePedidos detalle = service.update(id, dto);
        DetallePedidosDTO resultDto = DetallePedidosDTO.builder()
                .id(detalle.getId())
                .cantidad(detalle.getCantidad())
                .subtotal(detalle.getSubtotal())
                .producto(detalle.getProducto().getNombre())
                .pedidoId(detalle.getPedido().getId())
                .build();
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
