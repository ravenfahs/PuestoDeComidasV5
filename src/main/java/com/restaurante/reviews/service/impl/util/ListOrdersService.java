package com.restaurante.reviews.service.impl.util;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.util.MapperOrden;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.OrderFoodsRepository;

import java.util.ArrayList;
import java.util.List;

public class ListOrdersService {
    private final ListFoodOfOrderService listFoodOfOrderService;

    public ListOrdersService(OrderFoodsRepository orderFoodsRepository) {
        this.listFoodOfOrderService = new ListFoodOfOrderService(orderFoodsRepository);
    }

    public List<OrderDTO> listOrder(List<Order> modelOrder) {

        List<OrderDTO> listOrdersDTO = new ArrayList<>();

        modelOrder.forEach(
                order -> {
                    OrderDTO orderDTO;
                    orderDTO = MapperOrden.mapToOrderDTO(order);

                    listOrdersDTO.add(
                            MapperOrden.mapFoodsOfOrderDTO(orderDTO, listFoodOfOrderService.listFoods(order.getId()))
                    );
                }
        );

        return listOrdersDTO;
    }
}
