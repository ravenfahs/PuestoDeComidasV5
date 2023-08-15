package com.restaurante.reviews.controllers;


import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.service.*;
import com.restaurante.reviews.service.impl.*;
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



    @PostMapping("/api/order")
    public ResponseEntity<String> createOrden(@RequestBody OrderRequestDTO orderRequestDTO){

       CreateOrderService orderService = new CreateOrderServiceImpl(orderRepository,
                                                                                                clientRepository,
                                                                                                foodsRepository,
                                                                                                foodStellRepository,
                                                                                                orderFoodsRepository);

        return orderService.createOrden(orderRequestDTO);
    }

    @GetMapping("/api/order")
    public List<OrderDTO> getAllOrder(){

        Long userID = 5L;
        GetAllOrderService getAllOrderService = new GetAllOrderServiceImpl(orderRepository, orderFoodsRepository);

        return getAllOrderService.getAllOrder(userID, foodStellRepository, clientRepository);
    }

    @GetMapping("/api/order/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {

        GetOrderByIdService getOrderService = new GetOrderByIdServiceImpl(orderRepository, orderFoodsRepository);

        return getOrderService.getOrderbyId(id);
    }

    @PutMapping("/api/order/{id}")
    public ResponseEntity<String> updateStateOrder(@PathVariable Long id) {

        Long userID = 5L;
        UpdateOrderStatusService updateOrderStatusService = new UpdateOrderStatusServiceImpl(orderRepository);

        return updateOrderStatusService.updateOrder(userID, id, foodStellRepository , clientRepository);
    }

    @DeleteMapping("/api/order/{id}")
    public ResponseEntity<String> softDeleteStateOrder(@PathVariable Long id) {

        SoftDeleteOrderStatusService softDeleteOrderStatusService =
                new SoftDeleteOrderStatusServiceImpl(orderRepository);

        return softDeleteOrderStatusService.softDeleteOrder(id);
    }

}
