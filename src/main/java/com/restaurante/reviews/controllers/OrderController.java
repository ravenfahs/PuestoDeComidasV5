package com.restaurante.reviews.controllers;


import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.security.JWTUtil;
import com.restaurante.reviews.security.ValidateToken;
import com.restaurante.reviews.service.order.util.VerifyOrderStatus;
import com.restaurante.reviews.service.order.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

   private final GetAllOrderByStatusService getAllOrderByStatusService;
   private final GetAllOrderService getAllOrderService;
   private  final GetOrderByIdService getOrderService;
   private final UpdateOrderStatusService updateOrderStatusService;
   private final SoftDeleteOrderStatusService softDeleteOrderStatusService;
   private final CreateOrderService createOrderService;
   private final ValidateToken validateToken;

    public OrderController(GetAllOrderByStatusService getAllOrderByStatusService,
                           GetAllOrderService getAllOrderService,
                           GetOrderByIdService getOrderService,
                           UpdateOrderStatusService updateOrderStatusService,
                           SoftDeleteOrderStatusService softDeleteOrderStatusService,
                           CreateOrderService createOrderService,
                           JWTUtil jwtUtil) {

        this.getAllOrderByStatusService = getAllOrderByStatusService;
        this.getAllOrderService = getAllOrderService;
        this.getOrderService = getOrderService;
        this.updateOrderStatusService = updateOrderStatusService;
        this.softDeleteOrderStatusService = softDeleteOrderStatusService;
        this.createOrderService = createOrderService;
        validateToken = new ValidateToken(jwtUtil);

    }

    @PostMapping("/api/order")
    public ResponseEntity<String> createOrden(@RequestHeader(value="Authorization") String token,
                                                                    @RequestBody OrderRequestDTO orderRequestDTO){

        if (!validateToken.isValidToken(token)) return null;

        Long clientID = validateToken.getUserID();

        return createOrderService.createOrden(clientID, orderRequestDTO);
    }

    @GetMapping("/api/order")
    public List<OrderDTO> getAllOrder(@RequestHeader(value="Authorization") String token,
                                                        @RequestParam(required = false) String status){

        if (!validateToken.isValidToken(token)) return null;

        Long userID = validateToken.getUserID();

        if (status != null && VerifyOrderStatus.statusOfOrder(status.toUpperCase())) {

            return getAllOrderByStatusService.getAllOrderByStatus(
                        userID,
                        VerifyOrderStatus.statusVerified()
            );
        }

        return getAllOrderService.getAllOrder(userID);
    }

    @GetMapping("/api/order/id/{id}")
    public OrderDTO getOrder(@RequestHeader(value="Authorization") String token,
                                            @PathVariable Long id) {

        if (!validateToken.isValidToken(token)) return null;

        Long userID = validateToken.getUserID();

        return getOrderService.getOrderbyId(id, userID);
    }

    @PutMapping("/api/order/{id}")
    public ResponseEntity<String> updateStateOrder(@RequestHeader(value="Authorization") String token,
                                                                            @PathVariable Long id) {

        if (!validateToken.isValidToken(token)) return null;

        Long userID = validateToken.getUserID();

        return updateOrderStatusService.updateOrder(userID, id);
    }

    @DeleteMapping("/api/order/{id}")
    public ResponseEntity<String> softDeleteStateOrder(@RequestHeader(value="Authorization") String token,
                                                                                @PathVariable Long id) {

        if (!validateToken.isValidToken(token)) return null;

        return softDeleteOrderStatusService.softDeleteOrder(id);
    }


}
