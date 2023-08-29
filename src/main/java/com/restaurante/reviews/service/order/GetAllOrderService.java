package com.restaurante.reviews.service.order;

import com.restaurante.reviews.DTO.OrderDTO;

import java.util.List;

public interface GetAllOrderService {

    List<OrderDTO> getAllOrder(Long userID);
}
