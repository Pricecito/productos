package com.api.productos.controller;

import com.api.productos.dto.DetallePedidosDTO;
import com.api.productos.model.DetallePedidos;
import com.api.productos.service.DetallePedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/detallepedidos")
public class DetallePedidosController {
    @Autowired
    private DetallePedidosService service;

    @GetMapping
    public ResponseEntity<List<DetallePedidosDTO>> findAll() {
        List<DetallePedidos> list=service.findAll();
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<DetallePedidosDTO> listDto=list.stream().map( detalles -> DetallePedidosDTO.builder()
                .id(detalles.getId())
                .cantidad(detalles.getCantidad())
                .subtotal(detalles.getSubtotal())
                .producto(detalles.getProducto().getNombre())
                .pedidoId(detalles.getPedido().getId())
                .build()).toList();
        return ResponseEntity.ok(listDto);
    }


}
