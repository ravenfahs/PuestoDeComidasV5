package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.FoodOnOrderDTO;
import com.restaurante.reviews.DTO.OrdenRequestDTO;
import com.restaurante.reviews.mappers.MapperOrdenFoods;
import com.restaurante.reviews.models.Comestibles;
import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.repository.ComestiblesRepository;
import com.restaurante.reviews.repository.OrdenComesRepository;

public class CreateOrderFoodService {

    private final ComestiblesRepository foodRepository;
    private final OrdenComesRepository ordenFoodsRepository;
    private double totalValueFoods;

    public CreateOrderFoodService(ComestiblesRepository foodRepository,
                                  OrdenComesRepository ordenFoodsRepository) {

        this.foodRepository = foodRepository;
        this.ordenFoodsRepository = ordenFoodsRepository;
    }

    public void createOrdenFoods(OrdenRequestDTO ordenRequestDTO, Orden newOrden){

        for (FoodOnOrderDTO foodOnOrderDTO : ordenRequestDTO.getFoods()){

            Comestibles food = foodRepository.findById(foodOnOrderDTO.getIdFood()).orElse(null);

            ordenFoodsRepository.save(
                    MapperOrdenFoods.mapToOrdenFood(food, newOrden, foodOnOrderDTO)
            );

            totalValueFoods += food.getPrecio() * foodOnOrderDTO.getQuantity();
        }
    }

    public double getTotal() {
        return totalValueFoods ;
    }


}
