package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CreateOrderService {
    ResponseEntity<String> createOrden(OrderRequestDTO orderRequestDTO);
}
