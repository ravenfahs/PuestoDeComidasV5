package atl.bootcamp.e9.savorspot.service.food;


import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateFoodService {
    //update in Controller
    FoodDTO updateFood(FoodDTO Food, long id, Long foodStallId);
}
