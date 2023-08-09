package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.repository.ClientRepository;
import com.restaurante.reviews.repository.FoodStellRepository;
import com.restaurante.reviews.repository.UserRepository;

import java.util.List;

public interface GetAllOrderService {

    List<OrderDTO> getAllOrder(Long id, FoodStellRepository foodStallRepository,
                               ClientRepository clientRepository);
}
