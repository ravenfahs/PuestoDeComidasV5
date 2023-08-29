package com.restaurante.reviews.service.order;

import com.restaurante.reviews.DTO.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CreateOrderService {
    ResponseEntity<String> createOrden(Long clientID, OrderRequestDTO orderRequestDTO);
}
