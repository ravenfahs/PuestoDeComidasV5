package com.restaurante.reviews.service.order;

import com.restaurante.reviews.DTO.OrderDTO;

public interface GetOrderByIdService {
    OrderDTO getOrderbyId(Long id, Long userID);
}
