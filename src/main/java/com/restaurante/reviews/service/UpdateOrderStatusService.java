package com.restaurante.reviews.service;

import com.restaurante.reviews.repository.ClientRepository;
import com.restaurante.reviews.repository.FoodStellRepository;
import org.springframework.http.ResponseEntity;

public interface UpdateOrderStatusService {

    ResponseEntity<String> updateOrder(Long userID, Long id,
                                       FoodStellRepository foodStallRepository,
                                       ClientRepository clientRepository);
}
