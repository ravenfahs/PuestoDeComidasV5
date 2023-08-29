package com.restaurante.reviews.service.order;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.OrderStatus;

import java.util.List;

public interface GetAllOrderByStatusService {

    List<OrderDTO> getAllOrderByStatus(Long userID, OrderStatus orderStatus);
}
