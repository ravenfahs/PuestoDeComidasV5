package atl.bootcamp.e9.savorspot.service.food.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import atl.bootcamp.e9.savorspot.exceptions.FoodNotFoundException;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.repository.FoodsRepository;
import atl.bootcamp.e9.savorspot.service.food.GetFoodByIdService;
import org.springframework.stereotype.Service;

@Service
public class GetFoodByIdServiceImpl implements GetFoodByIdService {

    private final FoodsRepository foodsRepository;

    public GetFoodByIdServiceImpl(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }

    @Override
    public FoodDTO getFoodById(Long foodId, Long foodStallId) {

        Foods foods = foodsRepository.findByIdAndFoodStall_Id(foodId, foodStallId)
                .orElseThrow(() ->
                new FoodNotFoundException("Food with ID: "+foodId+ " not found, for the Food Stall con ID: "+foodStallId));

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(foods.getName());
        foodDTO.setPrice(foods.getPrice());
        foodDTO.setDescription(foods.getDescription());
        return foodDTO;
    }
}
