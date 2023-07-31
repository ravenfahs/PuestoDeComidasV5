package com.restaurante.reviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ordenComestibles")
public class OrdenComestibles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "id_orden")
    @JsonIgnore
    private Orden orden;

    @ManyToOne()
    @JoinColumn(name = "id_producto")
    private Comestibles comestibles;

    @Column(name = "cantidad")
    private int cantidad;

}
