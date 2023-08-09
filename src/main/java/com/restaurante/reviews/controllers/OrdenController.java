package com.restaurante.reviews.controllers;


import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.service.CreateOrderService;
import com.restaurante.reviews.service.GetAllOrderService;
import com.restaurante.reviews.service.GetOrderByIdService;
import com.restaurante.reviews.service.impl.CreateOrderServiceImpl;
import com.restaurante.reviews.service.impl.GetAllOrderServiceImpl;
import com.restaurante.reviews.service.impl.GetOrderByIdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FoodsRepository foodsRepository;
    @Autowired
    private FoodStellRepository foodStellRepository;
    @Autowired
    private OrderFoodsRepository orderFoodsRepository;



    @PostMapping("/api/orden")
    public ResponseEntity<String> registrarOrden(@RequestBody OrderRequestDTO orderRequestDTO){

       CreateOrderService orderService = new CreateOrderServiceImpl(orderRepository,
                                                                                                clientRepository,
                                                                                                foodsRepository,
                                                                                                foodStellRepository,
               orderFoodsRepository);

        return orderService.createOrden(orderRequestDTO);
    }

    @GetMapping("/api/orden")
    public List<OrderDTO> getAllOrder(){

        GetAllOrderService getAllOrderService = new GetAllOrderServiceImpl(orderRepository, orderFoodsRepository);

        return getAllOrderService.getAllOrder(4L, foodStellRepository, clientRepository);
    }

    @GetMapping("/api/orden/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {

        GetOrderByIdService getOrderService = new GetOrderByIdServiceImpl(orderRepository, orderFoodsRepository);

        return getOrderService.getOrderbyId(id);
    }

}
