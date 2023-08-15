package com.restaurante.reviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "foodStall_id")
    @JsonIgnore
    private FoodStall foodStall;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "time_delivery")
    private String timeDelivery;

    @Column(name = "total")
    private double total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected OrderStatus state;

    /*
    @OneToMany( mappedBy = "orden")
    private List<OrdenComestibles> ordenComestiblesList = new ArrayList<>();
     */
}
