package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.FoodOnOrderDTO;
import com.restaurante.reviews.exceptions.FoodNotFoundException;
import com.restaurante.reviews.util.MapperOrdenFoods;
import com.restaurante.reviews.models.Foods;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.FoodsRepository;
import com.restaurante.reviews.repository.OrderFoodsRepository;
import com.restaurante.reviews.service.CreateOrderFoodService;

import java.util.List;

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
    public void createOrdenFoods(List<FoodOnOrderDTO> listFoodOnOrderDTO, Order newOrden){

        totalValueFoods = 0;

         listFoodOnOrderDTO.forEach(
                (FoodOnOrderDTO foodOnOrderDTO) -> {

                    Foods food = foodRepository.findById(foodOnOrderDTO.getIdFood())
                            .orElseThrow(()-> new FoodNotFoundException("Food not found with ID: "+ foodOnOrderDTO.getIdFood()));

                    orderFoodsRepository.save(
                            MapperOrdenFoods.mapToOrdenFood(food, newOrden, foodOnOrderDTO)
                    );

                    totalValueFoods += food.getPrice() * foodOnOrderDTO.getQuantity();
                }
        );
    }

    @Override
    public double getTotal() {
        return totalValueFoods ;
    }


}
