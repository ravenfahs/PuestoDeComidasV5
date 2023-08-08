package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.FoodOnOrderDTO;
import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.mappers.MapperOrdenFoods;
import com.restaurante.reviews.models.Foods;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.FoodsRepository;
import com.restaurante.reviews.repository.OrderFoodsRepository;
import com.restaurante.reviews.service.CreateOrderFoodService;

public class CreateOrderFoodServiceImpl implements CreateOrderFoodService {

    private final FoodsRepository foodRepository;
    private final OrderFoodsRepository orderFoodsRepository;
    private double totalValueFoods;

    public CreateOrderFoodServiceImpl(FoodsRepository foodRepository,
                                      OrderFoodsRepository orderFoodsRepository) {

        this.foodRepository = foodRepository;
        this.orderFoodsRepository = orderFoodsRepository;
    }

    @Override
    public void createOrdenFoods(OrderRequestDTO orderRequestDTO, Order newOrden){

        orderRequestDTO.getFoods().forEach(
                (FoodOnOrderDTO foodOnOrderDTO) -> {

                    Foods food = foodRepository.findById(foodOnOrderDTO.getIdFood()).orElse(null);

                    orderFoodsRepository.save(
                            MapperOrdenFoods.mapToOrdenFood(food, newOrden, foodOnOrderDTO)
                    );

                    totalValueFoods += food.getPrice() * foodOnOrderDTO.getQuantity();
                }
        );

     /*   for (FoodOnOrderDTO foodOnOrderDTO : ordenRequestDTO.getFoods()){

            Comestibles food = foodRepository.findById(foodOnOrderDTO.getIdFood()).orElse(null);

            ordenFoodsRepository.save(
                    MapperOrdenFoods.mapToOrdenFood(food, newOrden, foodOnOrderDTO)
            );

            totalValueFoods += food.getPrecio() * foodOnOrderDTO.getQuantity();
        }*/
    }

    @Override
    public double getTotal() {
        return totalValueFoods ;
    }


}
