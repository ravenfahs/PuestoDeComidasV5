package atl.bootcamp.e9.savorspot.service.food;

import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface GetFoodByIdService {
    //getById in Controller
    FoodDTO getFoodById(Long foodId, Long foodStallId);
}
