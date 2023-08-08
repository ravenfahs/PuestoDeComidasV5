package com.restaurante.reviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orderfoods")
public class OrderFoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "food_id")
    private Foods food;

    @Column(name = "cantidad")
    private int quantity;

}
