package com.restaurante.reviews.mappers;

import com.restaurante.reviews.DTO.FoodOnOrderDTO;
import com.restaurante.reviews.DTO.OrderFoodsDTO;
import com.restaurante.reviews.models.Comestibles;
import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.OrdenComestibles;


public final class MapperOrdenFoods {

    private MapperOrdenFoods() {
        throw new UnsupportedOperationException("A static class cannot be instantiated");
    }

    public static OrdenComestibles mapToOrdenFood(Comestibles food,
                                                                          Orden newOrden,
                                                                          FoodOnOrderDTO foodOnOrderDTO) {

        OrdenComestibles orderFoods = new OrdenComestibles();

        orderFoods.setOrden(newOrden);
        orderFoods.setComestibles(food);
        orderFoods.setCantidad(foodOnOrderDTO.getQuantity());

        return orderFoods;
    }

    public static OrderFoodsDTO mapToOrderFoodDTO(OrdenComestibles orderFoods){

        return new OrderFoodsDTO(
                                    orderFoods.getComestibles().getNombre(),
                                    orderFoods.getComestibles().getDescripcion(),
                                    orderFoods.getCantidad(),
                                    orderFoods.getComestibles().getPrecio());
    }
}
