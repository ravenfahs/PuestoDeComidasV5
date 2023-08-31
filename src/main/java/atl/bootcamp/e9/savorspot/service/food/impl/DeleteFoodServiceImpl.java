package atl.bootcamp.e9.savorspot.service.food.impl;

import atl.bootcamp.e9.savorspot.exceptions.FoodNotFoundException;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.repository.FoodsRepository;
import atl.bootcamp.e9.savorspot.service.food.DeleteFoodService;
import org.springframework.stereotype.Service;

@Service
public class DeleteFoodServiceImpl implements DeleteFoodService {

    private final FoodsRepository foodsRepository;

    public DeleteFoodServiceImpl(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }
    @Override
    public void deleteFood(long id, Long foodStallId) {

        Foods existingFoods = foodsRepository.findByIdAndFoodStall_Id(id, foodStallId).orElseThrow(() ->
                new FoodNotFoundException("Food with ID: "+ id+ " not found, for the Food Stall con ID: "+ foodStallId));

        foodsRepository.delete(existingFoods);
    }
}
