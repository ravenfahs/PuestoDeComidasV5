package atl.bootcamp.e9.savorspot.service.foodStall;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.FoodStallDto;

public interface FindFoodStallByIdService {
    FoodStallDto findWith(Long id);
}
