package com.restaurante.reviews.service;

import com.restaurante.reviews.models.*;
import com.restaurante.reviews.models.models_auxiliar.OrdenRequest;
import com.restaurante.reviews.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class CrearOrdenService {

    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;
    private CrearOrdenComidaService crearOrdenComidaService;

    public CrearOrdenService(OrdenRepository ordenRepository,
                                         ClienteRepository clienteRepository,
                                         ComestiblesRepository comestiblesRepository,
                                         RestauranteRepository restauranteRepository,
                                         OrdenComesRepository ordenComesRepository) {

        this.ordenRepository = ordenRepository;
        this.clienteRepository = clienteRepository;
        this.restauranteRepository = restauranteRepository;

        crearOrdenComidaService = new CrearOrdenComidaService(comestiblesRepository,
                                                                                                ordenComesRepository);
    }

    public ResponseEntity<String> crearOrden(OrdenRequest ordenRequest) {

        try {
            Orden nuevaOrden = new Orden();

            Long idCliente = 3L;
            Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
            Restaurante restaurante = restauranteRepository.findById(ordenRequest.getIdRestaurante()).orElse(null);

            nuevaOrden.setCliente(cliente);
            nuevaOrden.setEstado(ordenRequest.getEstado());
            nuevaOrden.setFechaHora(LocalDateTime.of(LocalDate.now(), LocalTime.now()) );
            nuevaOrden.setTiempoEntrega(ordenRequest.getTiempoEntrega());
            nuevaOrden.setRestaurante(restaurante);
            nuevaOrden = ordenRepository.save(nuevaOrden);
            crearOrdenComidaService.crearOrdenComida(ordenRequest, nuevaOrden);
            nuevaOrden.setPrecioTotal(crearOrdenComidaService.getTotalValorComestibles());
            ordenRepository.save(nuevaOrden);

            return ResponseEntity.ok("Orden creada exitosamente.");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ofNullable(e.getMessage());
        }
    }
}
