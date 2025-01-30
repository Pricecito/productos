package com.api.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;


}
