package com.restaurante.reviews.models.modeloDTO;

import com.restaurante.reviews.models.Cliente;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdenDTO {

    private Long id;
    private String estado;
    private LocalDateTime fecha_hora;
    private String tiempoEntrega;
    private double total;
    private Cliente cliente;
    private List<OrdenComestibleDTO> comidas;
}
