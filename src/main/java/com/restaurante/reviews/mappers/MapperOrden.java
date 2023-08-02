package com.restaurante.reviews.mappers;

import com.restaurante.reviews.DTO.OrdenRequestDTO;
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

    public static Orden mapToOrden(OrdenRequestDTO ordenRequestDTO, Cliente client, Restaurante foodStall){

        Orden newOrden = new Orden();

        newOrden.setCliente(client);
        newOrden.setEstado(ordenRequestDTO.getState());
        newOrden.setFechaHora(LocalDateTime.of(LocalDate.now(), LocalTime.now()) );
        newOrden.setTiempoEntrega(ordenRequestDTO.getTimeDelivery());
        newOrden.setRestaurante(foodStall);

        return newOrden;
    }

    public static Orden mapOrderTotal(Orden orden, double total) {
        orden.setPrecioTotal(total);
        return orden;
    }

    public static OrderDTO mapToOrderDTO(Orden order){

         return new OrderDTO(order.getId(),
                order.getEstado(),
                order.getFechaHora(),
                order.getTiempoEntrega(),
                order.getPrecioTotal(),
                order.getCliente());
    }

    public static OrderDTO mapFoodsOfOrderDTO(OrderDTO orderDTO, List<OrderFoodsDTO> allFoods) {

        orderDTO.setFoods(allFoods);

        return orderDTO;
    }
}
