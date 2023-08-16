package com.restaurante.reviews.controllers;


import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.OrderStatus;
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

   private final GetAllOrderByStatusService getAllOrderByStatusService;
   private final GetAllOrderService getAllOrderService;
   private  final GetOrderByIdService getOrderService;
   private final UpdateOrderStatusService updateOrderStatusService;
   private final SoftDeleteOrderStatusService softDeleteOrderStatusService;
   private final CreateOrderService orderService;

    public OrdenController(GetAllOrderByStatusService getAllOrderByStatusService,
                                       GetAllOrderService getAllOrderService,
                                       GetOrderByIdService getOrderService,
                                       UpdateOrderStatusService updateOrderStatusService,
                                       SoftDeleteOrderStatusService softDeleteOrderStatusService,
                                        CreateOrderService orderService) {

        this.getAllOrderByStatusService = getAllOrderByStatusService;
        this.getAllOrderService = getAllOrderService;
        this.getOrderService = getOrderService;
        this.updateOrderStatusService = updateOrderStatusService;
        this.softDeleteOrderStatusService = softDeleteOrderStatusService;
        this.orderService = orderService;
    }

    @PostMapping("/api/order")
    public ResponseEntity<String> createOrden(@RequestBody OrderRequestDTO orderRequestDTO){

        return orderService.createOrden(orderRequestDTO);
    }

    @GetMapping("/api/order")
    public List<OrderDTO> getAllOrder(@RequestParam(required = false) OrderStatus status){

        Long userID = 5L;

        if (status != null) {
            return getAllOrderByStatusService.getAllOrderByStatus(userID,status);
        }

        return getAllOrderService.getAllOrder(userID);
    }

    @GetMapping("/api/order/id/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {

        Long userID = 5L;

        return getOrderService.getOrderbyId(id, userID);
    }

    @PutMapping("/api/order/{id}")
    public ResponseEntity<String> updateStateOrder(@PathVariable Long id) {

        Long userID = 5L;

        return updateOrderStatusService.updateOrder(userID, id);
    }

    @DeleteMapping("/api/order/{id}")
    public ResponseEntity<String> softDeleteStateOrder(@PathVariable Long id) {

        return softDeleteOrderStatusService.softDeleteOrder(id);
    }


}
