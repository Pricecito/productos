package com.api.productos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePedidosDTO {

    private Long id;
    private Long cantidad;
    private Double subtotal;
    private Long pedidoId;
    private Long productoId;

}
