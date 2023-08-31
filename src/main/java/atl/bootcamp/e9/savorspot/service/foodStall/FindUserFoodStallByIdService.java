package atl.bootcamp.e9.savorspot.service.foodStall;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.UserFoodStallDto;

public interface FindUserFoodStallByIdService {
   UserFoodStallDto findWith(Long id);
}
