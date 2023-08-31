package atl.bootcamp.e9.savorspot.service.food.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import atl.bootcamp.e9.savorspot.exceptions.FoodNotFoundException;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.repository.FoodsRepository;
import atl.bootcamp.e9.savorspot.service.food.UpdateFoodService;
import org.springframework.stereotype.Service;

@Service
public class UpdateFoodServiceImpl implements UpdateFoodService {
    private final FoodsRepository foodsRepository;

    public UpdateFoodServiceImpl(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }
    @Override
    public FoodDTO updateFood (FoodDTO foodDTO, long id, Long foodStallId) {

        Foods existingFoods = foodsRepository.findByIdAndFoodStall_Id(id, foodStallId) .orElseThrow(() ->
                new FoodNotFoundException("Food with ID: "+ id+ " not found, for the Food Stall con ID: "+ foodStallId));
        //updating existing Foods
        existingFoods.setName(foodDTO.getName());
        existingFoods.setPrice(foodDTO.getPrice());
        existingFoods.setDescription(foodDTO.getDescription());
        //saving existing Foods

        foodsRepository.save(existingFoods);
        return foodDTO;

    }
}
