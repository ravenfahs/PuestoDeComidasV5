package com.restaurante.reviews.service.order;

import org.springframework.http.ResponseEntity;

public interface SoftDeleteOrderStatusService {

    ResponseEntity<String> softDeleteOrder(Long id);
}
