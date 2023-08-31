package atl.bootcamp.e9.savorspot.service.food;

import org.springframework.stereotype.Repository;

@Repository
public interface DeleteFoodService {
    void deleteFood(long id, Long foodStallId);
}
