package com.restaurante.reviews.service;

import org.springframework.http.ResponseEntity;

public interface SoftDeleteOrderStatusService {

    ResponseEntity<String> softDeleteOrder(Long id);
}
