package com.restaurante.reviews.models;

import jakarta.persistence.*;
import lombok.Data;
import java.awt.geom.Point2D;
import java.util.List;

@Data
@Entity
@Table(name = "restaurante")
@DiscriminatorValue("restaurante")
public class Restaurante extends Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "tipo_cocina")
    private String tipoCocina;

    @Column(name = "horarios")
    private String horarios;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "imagen_fachada")
    private String imagenFachada;

    /*@OneToMany(mappedBy = "restaurante")
    private List<Comestibles> comestiblesList;*/

    @Column(name = "ubicacion", columnDefinition = "POINT")
    private Point2D.Double coordenadas;

}
