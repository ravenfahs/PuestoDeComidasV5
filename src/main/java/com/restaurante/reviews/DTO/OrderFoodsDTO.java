package com.restaurante.reviews.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderFoodsDTO {

    private String name;
    private String description;
    private int quantity;
    private double price;
}
