package com.restaurante.reviews.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDTO {

    private String state;
    private String timeDelivery;
    private Long idFoodStall;
    private List<FoodOnOrderDTO> foods;
}
