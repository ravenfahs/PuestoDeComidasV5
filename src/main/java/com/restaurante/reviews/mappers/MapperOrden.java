package com.restaurante.reviews.mappers;

import com.restaurante.reviews.DTO.ClientDTOBasico;
import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.DTO.OrderFoodsDTO;
import com.restaurante.reviews.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public final class MapperOrden {

    private MapperOrden() {
        throw new UnsupportedOperationException("A static class cannot be instantiated");
    }

    public static Order mapToOrden(OrderRequestDTO orderRequestDTO, Client client, FoodStall foodStall){

        Order newOrden = new Order();

        newOrden.setClient(client);
        newOrden.setState(orderRequestDTO.getState());
        newOrden.setDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()) );
        newOrden.setTimeDelivery(orderRequestDTO.getTimeDelivery());
        newOrden.setFoodStall(foodStall);

        return newOrden;
    }

    public static Order mapOrderTotal(Order order, double total) {
        order.setTotal(total);
        return order;
    }

    public static OrderDTO mapToOrderDTO(Order order){

         return new OrderDTO(
                 order.getId(),
                order.getState(),
                order.getDateTime(),
                order.getTimeDelivery(),
                order.getTotal(),
                new ClientDTOBasico(
                        order.getClient().getId(),
                        order.getClient().getFullName(),
                        order.getClient().getUserStatus().toString()
                )
         );
    }

    public static OrderDTO mapFoodsOfOrderDTO(OrderDTO orderDTO, List<OrderFoodsDTO> allFoods) {

        orderDTO.setFoods(allFoods);

        return orderDTO;
    }
}
