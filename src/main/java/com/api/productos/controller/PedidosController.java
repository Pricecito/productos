package com.api.productos.controller;

import com.api.productos.dto.PedidoDTO;
import com.api.productos.model.Pedidos;
import com.api.productos.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService service;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() {
        List<Pedidos> listPedidos=service.findAll();
        if(listPedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PedidoDTO> listDto= listPedidos.stream().map(pedido->PedidoDTO.builder()
                .id(pedido.getId().toString())
                .fecha_creacion(pedido.getFecha_Creacion().toString())
                .estado(pedido.getEstado().name())
                .total(pedido.getTotal())
                .usuario(pedido.getUsuario().getNombre())
                .build()).toList();
        return ResponseEntity.ok(listDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO>findById(UUID id){
        Pedidos pedido=service.findById(id).get();
        if(Objects.isNull(pedido)){
            return ResponseEntity.notFound().build();
        }
        PedidoDTO pedidoDTO=PedidoDTO.builder()
                .id(pedido.getId().toString())
                .fecha_creacion(pedido.getFecha_Creacion().toString())
                .estado(pedido.getEstado().name())
                .total(pedido.getTotal())
                .usuario(pedido.getUsuario().getNombre())
                .build();
        return ResponseEntity.ok(pedidoDTO);
    }

    @PostMapping
    public ResponseEntity<Pedidos> save(@RequestBody Pedidos pedido) {
        Pedidos savedPedido = service.save(pedido);
        URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPedido.getId()).toUriString());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Pedidos> update(@RequestBody Pedidos pedido) {
        Pedidos existingPedido = service.findById(pedido.getId()).orElse(null);
        if (Objects.nonNull(existingPedido)) {
            service.save(pedido);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedidos> updatePartial(@PathVariable("id") UUID id, @RequestBody Pedidos pedido) {
        Pedidos existingPedido = service.findById(id).orElse(null);
        if (Objects.nonNull(existingPedido)) {
            if (Objects.nonNull(pedido.getFecha_Creacion())) existingPedido.setFecha_Creacion(pedido.getFecha_Creacion());
            if (Objects.nonNull(pedido.getEstado())) existingPedido.setEstado(pedido.getEstado());
            if (Objects.nonNull(pedido.getTotal())) existingPedido.setTotal(pedido.getTotal());
            service.save(existingPedido);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedidos> delete(@PathVariable("id") UUID id) {
        Pedidos existingPedido = service.findById(id).orElse(null);
        if (Objects.nonNull(existingPedido)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
