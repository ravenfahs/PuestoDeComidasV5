package atl.bootcamp.e9.savorspot.service.food.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import atl.bootcamp.e9.savorspot.exceptions.FoodStallNotFoundException;
import atl.bootcamp.e9.savorspot.models.FoodStall;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.repository.FoodsRepository;
import atl.bootcamp.e9.savorspot.service.food.CreateFoodService;
import org.springframework.stereotype.Service;

@Service
public class CreateFoodServiceImpl implements CreateFoodService {

    private final FoodsRepository foodsRepository;
    private final FoodStallRepository foodStallRepository;

    public CreateFoodServiceImpl(FoodsRepository foodsRepository, FoodStallRepository foodStallRepository) {
        this.foodsRepository = foodsRepository;
        this.foodStallRepository = foodStallRepository;
    }

    @Override
    public FoodDTO createFood(FoodDTO foodDto, Long foodStallId) {

        FoodStall foodStall = foodStallRepository.findById(foodStallId)
                .orElseThrow(() -> new FoodStallNotFoundException("Food stall with id " + foodStallId + " not found"));

        Foods foods = new Foods();
        foods.setName(foodDto.getName());
        foods.setPrice(foodDto.getPrice());
        foods.setDescription(foodDto.getDescription());
        foods.setFoodDrink(foodDto.getFoodDrink());
        foods.setImage(foods.getImage());

        foods.setFoodStall(foodStall);

        foodsRepository.save(foods);
        return foodDto;
    }
}
