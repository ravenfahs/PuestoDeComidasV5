package com.restaurante.reviews.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comestibles")
public class Comestibles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private double precio;

    @Column(name = "imagen_plato")
    private String imagenPlato;

    // Relación muchos a uno con Restaurante
    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

}
