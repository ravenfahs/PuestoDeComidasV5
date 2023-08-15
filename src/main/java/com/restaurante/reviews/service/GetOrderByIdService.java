package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderDTO;

public interface GetOrderByIdService {
    OrderDTO getOrderbyId(Long id);
}
