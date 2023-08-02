package com.restaurante.reviews.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class OrdenRequestDTO {

    private String state;
    private String timeDelivery;
    private Long idFoodStall;
    private List<FoodOnOrderDTO> foods;
}
