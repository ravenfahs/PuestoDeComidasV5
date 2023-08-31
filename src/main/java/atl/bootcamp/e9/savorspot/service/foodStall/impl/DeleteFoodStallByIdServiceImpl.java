package atl.bootcamp.e9.savorspot.service.foodStall.impl;

import atl.bootcamp.e9.savorspot.exceptions.FoodStallNotFoundException;
import atl.bootcamp.e9.savorspot.models.FoodStall;
import atl.bootcamp.e9.savorspot.models.UserStatus;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.service.foodStall.DeleteFoodStallByIdService;
import org.springframework.stereotype.Service;

@Service
public class DeleteFoodStallByIdServiceImpl implements DeleteFoodStallByIdService {

    private final FoodStallRepository foodStallRepository;

    public DeleteFoodStallByIdServiceImpl(FoodStallRepository foodStallRepository) {
        this.foodStallRepository = foodStallRepository;
    }

    @Override
    public void deleteWith(Long id) {

        FoodStall foodStallToUpdate = foodStallRepository
                .findByIdAndUserStatusNot(id, UserStatus.INACTIVE)
                .orElseThrow(() -> new FoodStallNotFoundException("Food stall with id " + id + " not found"));
        foodStallToUpdate.setUserStatus(UserStatus.INACTIVE);
        foodStallRepository.save(foodStallToUpdate);

    }
}
