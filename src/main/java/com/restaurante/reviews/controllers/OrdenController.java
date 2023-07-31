package com.restaurante.reviews.controllers;

import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.OrdenComestibles;
import com.restaurante.reviews.models.models_auxiliar.OrdenRequest;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.service.OrdenService;
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

        OrdenService ordenService = new OrdenService(ordenRepository,
                clienteRepository,
                comestiblesRepository,
                restauranteRepository,
                ordenComesRepository);

        return ordenService.crearOrden(ordenRequest);
    }

@GetMapping("/api/orden")
    public List<Orden> getOrdenes(){

     OrdenService ordenService = new OrdenService(ordenRepository,
                clienteRepository,
                comestiblesRepository,
                restauranteRepository,
                ordenComesRepository);

        return ordenService.getOrdenes();
    }

    @GetMapping("/api/orden/{id}")
    public List<OrdenComestibles> getOrden(@PathVariable Long id){

        OrdenService ordenService = new OrdenService(ordenRepository,
                clienteRepository,
                comestiblesRepository,
                restauranteRepository,
                ordenComesRepository);

        return ordenService.getOrden(id);
    }
}
