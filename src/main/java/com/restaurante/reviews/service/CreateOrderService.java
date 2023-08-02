package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrdenRequestDTO;
import com.restaurante.reviews.mappers.MapperOrden;
import com.restaurante.reviews.models.Cliente;
import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.Restaurante;
import com.restaurante.reviews.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService {

    private final OrdenRepository ordenRepository;
    private final ClienteRepository clientRepository;
    private final RestauranteRepository foodStallRepository;

    private CreateOrderFoodService createOrdenFoodService;


    public CreateOrderService(OrdenRepository ordenRepository,
                              ClienteRepository clientRepository,
                              ComestiblesRepository foodRepository,
                              RestauranteRepository foodStallRepository,
                              OrdenComesRepository ordenFoodRepository) {

        this.ordenRepository = ordenRepository;
        this.clientRepository = clientRepository;
        this.foodStallRepository = foodStallRepository;
        createOrdenFoodService = new CreateOrderFoodService(foodRepository, ordenFoodRepository);
    }

    public ResponseEntity<String> createOrden(OrdenRequestDTO ordenRequestDTO) {

        Long idClient= 3L;
        Cliente client = clientRepository.findById(idClient).orElse(null);
        Restaurante foodStall = foodStallRepository.findById(ordenRequestDTO.getIdFoodStall()).orElse(null);
        Orden orden;

        orden = ordenRepository.save(
                MapperOrden.mapToOrden(ordenRequestDTO, client, foodStall)
        );
        createOrdenFoodService.createOrdenFoods(ordenRequestDTO,orden);
        ordenRepository.save(
                MapperOrden.mapOrderTotal(orden, createOrdenFoodService.getTotal())
        );

        return ResponseEntity.ok("Orden creada exitosamente.");
    }
}
