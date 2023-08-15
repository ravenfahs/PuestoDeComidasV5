package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.FoodOnOrderDTO;
import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.models.Order;

import java.util.List;

public interface CreateOrderFoodService {

    void createOrdenFoods(List<FoodOnOrderDTO> listFoodOnOrderDTO, Order newOrden);
    double getTotal();
}
