package com.api.productos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePedidosDTO {

    private Long id;
    private Long cantidad;
    private Double subtotal;
    private UUID pedidoId;
    private String producto;

}
