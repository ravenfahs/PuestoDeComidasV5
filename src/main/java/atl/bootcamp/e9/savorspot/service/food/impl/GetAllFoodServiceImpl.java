package atl.bootcamp.e9.savorspot.service.food.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.repository.FoodsRepository;
import atl.bootcamp.e9.savorspot.service.food.GetAllFoodService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllFoodServiceImpl implements GetAllFoodService {

    private final FoodsRepository foodsRepository;

    public GetAllFoodServiceImpl(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }
    @Override
    public List<FoodDTO> getAllFoods(Long foodStallId) {

        List<Foods> all = foodsRepository.findFoodsByFoodStall_Id(foodStallId);
        List<FoodDTO> food = new ArrayList<>();

        for (Foods foods : all){
            FoodDTO foodDto = new FoodDTO();
            foodDto.setName(foods.getName());
            foodDto.setPrice(foods.getPrice());
            foodDto.setDescription(foods.getDescription());
            food.add(foodDto);
        }
        return food ;
    }
}
