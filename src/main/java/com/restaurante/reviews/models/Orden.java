package com.restaurante.reviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;



@Data
@Entity
@Table(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    @JsonIgnore
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "tiempo_entrega")
    private String tiempoEntrega;

    @Column(name = "precio_total")
    private double precioTotal;

    @Column(name = "estado")
    private String estado;

    /*
    @OneToMany( mappedBy = "orden")
    private List<OrdenComestibles> ordenComestiblesList = new ArrayList<>();
     */
}
