package com.restaurante.reviews.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodOnOrderDTO {
    private Long idFood;
    private int quantity;
}
