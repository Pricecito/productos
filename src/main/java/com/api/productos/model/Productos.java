package com.api.productos.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "productos")
public class Productos implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String nombre;

    private String descripcion;

    private double precio;

    private int stock;

    
    
}