package com.restaurante.reviews.service.order;

import org.springframework.http.ResponseEntity;

public interface UpdateOrderStatusService {

    ResponseEntity<String> updateOrder(Long userID, Long id);
}
