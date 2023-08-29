package com.restaurante.reviews.service.order.util;

import com.restaurante.reviews.DTO.OrderFoodsDTO;
import com.restaurante.reviews.service.order.mappers.MapperOrdenFoods;
import com.restaurante.reviews.models.OrderFoods;
import com.restaurante.reviews.repository.OrderFoodsRepository;

import java.util.ArrayList;
import java.util.List;

public class ListFoodOfOrderService {

    private final OrderFoodsRepository orderFoodsRepository;

    public ListFoodOfOrderService(OrderFoodsRepository orderFoodsRepository) {
        this.orderFoodsRepository = orderFoodsRepository;
    }

    public  List<OrderFoodsDTO> listFoods(Long id) {

        List<OrderFoods> modelOrderFoods = orderFoodsRepository.findByOrder_Id(id);
        List<OrderFoodsDTO> listFoods = new ArrayList<>();

        modelOrderFoods.forEach(
                orderFoods ->
                        listFoods.add(
                                MapperOrdenFoods.mapToOrderFoodDTO(orderFoods)
                        )
        );
        
        return listFoods;
    }
}
