package com.restaurante.reviews.models.models_auxiliar;

import lombok.Data;

import java.util.List;

@Data
public class OrdenRequest {
    private String estado;
    private String tiempoEntrega;
    private double total;
    private Long idRestaurante;

    private List<ComidaEnOrden> comidas;
}
