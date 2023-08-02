package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderFoodsDTO;
import com.restaurante.reviews.mappers.MapperOrdenFoods;
import com.restaurante.reviews.models.OrdenComestibles;
import com.restaurante.reviews.repository.OrdenComesRepository;

import java.util.ArrayList;
import java.util.List;

public class ListFoodOfOrderService {

    private final OrdenComesRepository orderFoodsRepository;

    public ListFoodOfOrderService(OrdenComesRepository orderFoodsRepository) {
        this.orderFoodsRepository = orderFoodsRepository;
    }

    public  List<OrderFoodsDTO> listFoods(Long id) {

        List<OrdenComestibles> modelOrderFoods = orderFoodsRepository.findByOrden_Id(id);
        List<OrderFoodsDTO> listFoods = new ArrayList<>();

        modelOrderFoods.forEach(
                orderFoods ->
                        listFoods.add(
                                MapperOrdenFoods.mapToOrderFoodDTO(orderFoods)
                        )
        );

       /* for (OrdenComestibles orderFoods: modelOrderFoods) {
                listFoods.add(MapperOrdenFoods.mapToOrderFoodDTO(orderFoods));
        }*/

        return listFoods;
    }
}
