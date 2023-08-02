package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.mappers.MapperOrden;
import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.repository.OrdenComesRepository;

import java.util.ArrayList;
import java.util.List;

public class ListOrdersService {
    private final ListFoodOfOrderService listFoodOfOrderService;

    public ListOrdersService(OrdenComesRepository orderFoodsRepository) {
        this.listFoodOfOrderService = new ListFoodOfOrderService(orderFoodsRepository);
    }

    public List<OrderDTO> listOrder(List<Orden> modelOrder) {

        List<OrderDTO> listOrdersDTO = new ArrayList<>();

        modelOrder.forEach(
                order -> {
                    OrderDTO orderDTO;
                    orderDTO = MapperOrden.mapToOrderDTO(order);

                    listOrdersDTO.add(
                            MapperOrden.mapFoodsOfOrderDTO(orderDTO, listFoodOfOrderService.listFoods(order.getId())
                            )
                    );
                }
        );

      /*  for (Orden order : modelOrder) {
            OrderDTO orderDTO;

            orderDTO = MapperOrden.mapToOrderDTO(order);
            listOrdersDTO.add(
                    MapperOrden.mapFoodsOfOrderDTO(
                            orderDTO,
                            listFoodOfOrderService.listFoods(order.getId())
                    )
            );
        }*/

        return listOrdersDTO;
    }
}
