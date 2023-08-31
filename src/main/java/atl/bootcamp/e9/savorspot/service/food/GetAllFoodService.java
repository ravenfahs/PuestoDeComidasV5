package atl.bootcamp.e9.savorspot.service.food;

import atl.bootcamp.e9.savorspot.DTO.FoodDTO.FoodDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GetAllFoodService {

    //getALL in Controller
    List<FoodDTO> getAllFoods(Long Foodstallid);
}
