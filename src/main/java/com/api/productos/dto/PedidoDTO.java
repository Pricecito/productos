package com.api.productos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
    private String id;
    private Double total;
    private String estado;
    private String fecha_creacion;
    private String usuario;
}
