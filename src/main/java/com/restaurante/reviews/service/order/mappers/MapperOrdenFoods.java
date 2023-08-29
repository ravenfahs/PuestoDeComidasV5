package com.restaurante.reviews.service.order.mappers;

import com.restaurante.reviews.DTO.FoodOnOrderDTO;
import com.restaurante.reviews.DTO.OrderFoodsDTO;
import com.restaurante.reviews.models.Foods;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.models.OrderFoods;


public final class MapperOrdenFoods {

    private MapperOrdenFoods() {
        throw new UnsupportedOperationException("A static class cannot be instantiated");
    }

    public static OrderFoods mapToOrdenFood(Foods food,
                                            Order newOrden,
                                            FoodOnOrderDTO foodOnOrderDTO) {

        OrderFoods orderFoods = new OrderFoods();

        orderFoods.setOrder(newOrden);
        orderFoods.setFood(food);
        orderFoods.setQuantity(foodOnOrderDTO.getQuantity());

        return orderFoods;
    }

    public static OrderFoodsDTO mapToOrderFoodDTO(OrderFoods orderFoods){

        return new OrderFoodsDTO(
                                    orderFoods.getFood().getName(),
                                    orderFoods.getFood().getDescription(),
                                    orderFoods.getQuantity(),
                                    orderFoods.getFood().getPrice());
    }
}
