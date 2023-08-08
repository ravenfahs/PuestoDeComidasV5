package com.restaurante.reviews.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foods")
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "photo_dish")
    private String photoDish;

    // Relación muchos a uno con Restaurante
    @ManyToOne
    @JoinColumn(name = "foodStall_id")
    private FoodStall foodStall;

}
