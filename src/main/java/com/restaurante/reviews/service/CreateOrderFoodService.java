package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.models.Order;

public interface CreateOrderFoodService {

    void createOrdenFoods(OrderRequestDTO orderRequestDTO, Order newOrden);
    double getTotal();
}
