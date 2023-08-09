package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.mappers.MapperOrden;
import com.restaurante.reviews.models.Client;
import com.restaurante.reviews.models.FoodStall;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.service.CreateOrderFoodService;
import com.restaurante.reviews.service.CreateOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final FoodStellRepository foodStallRepository;
    private final CreateOrderFoodService createOrdenFoodServiceImpl;


    public CreateOrderServiceImpl(OrderRepository orderRepository,
                                  ClientRepository clientRepository,
                                  FoodsRepository foodRepository,
                                  FoodStellRepository foodStallRepository,
                                  OrderFoodsRepository orderFoodRepository) {

        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.foodStallRepository = foodStallRepository;
        createOrdenFoodServiceImpl = new CreateOrderFoodServiceImpl(foodRepository, orderFoodRepository);
    }

    @Override
    public ResponseEntity<String> createOrden(OrderRequestDTO orderRequestDTO) {

        Long idClient= 4L;
        Client client = clientRepository.findById(idClient).orElse(null);
        FoodStall foodStall = foodStallRepository.findById(orderRequestDTO.getIdFoodStall()).orElse(null);
        Order newOrden;

        newOrden = orderRepository.save(
                MapperOrden.mapToOrden(orderRequestDTO, client, foodStall)
        );
        createOrdenFoodServiceImpl.createOrdenFoods(orderRequestDTO,newOrden);
        orderRepository.save(
                MapperOrden.mapOrderTotal(newOrden, createOrdenFoodServiceImpl.getTotal())
        );

        return ResponseEntity.ok("Orden creada exitosamente.");
    }
}
