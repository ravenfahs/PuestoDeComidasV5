package com.restaurante.reviews.controllers;

import com.restaurante.reviews.models.modeloDTO.OrdenDTO;
import com.restaurante.reviews.models.models_auxiliar.OrdenRequest;
import com.restaurante.reviews.ordenService.ObtenerOrdenByIdService;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.ordenService.CrearOrdenService;
import com.restaurante.reviews.ordenService.ObtenerOrdenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private ComestiblesRepository comestiblesRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private OrdenComesRepository ordenComesRepository;


    @PostMapping("/api/orden")
    public ResponseEntity<String> registrarOrden(@RequestBody OrdenRequest ordenRequest){

        CrearOrdenService ordenService = new CrearOrdenService(ordenRepository,
                clienteRepository,
                comestiblesRepository,
                restauranteRepository,
                ordenComesRepository);

        return ordenService.crearOrden(ordenRequest);
    }
    @GetMapping("/api/orden")
    public List<OrdenDTO> getAllOrdenes(){

        ObtenerOrdenesService obtenerOrdenesService = new ObtenerOrdenesService(ordenRepository,ordenComesRepository);

        return obtenerOrdenesService.getAllOrdenes();
    }

    @GetMapping("/api/orden/{id}")
    public OrdenDTO getOrdene(@PathVariable Long id){

        ObtenerOrdenByIdService obtenerOrdenByIdService = new ObtenerOrdenByIdService(ordenRepository,ordenComesRepository);

        return obtenerOrdenByIdService.getOrden(id);
    }
}
