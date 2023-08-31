package atl.bootcamp.e9.savorspot.service.foodStall.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.UserFoodStallDto;
import atl.bootcamp.e9.savorspot.exceptions.FoodStallNotFoundException;
import atl.bootcamp.e9.savorspot.models.UserStatus;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.service.foodStall.FindUserFoodStallByIdService;
import atl.bootcamp.e9.savorspot.service.foodStall.util.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class FindUserFoodStallByIdServiceImpl implements FindUserFoodStallByIdService {

    private final FoodStallRepository foodStallRepository;

    public FindUserFoodStallByIdServiceImpl(FoodStallRepository foodStallRepository) {
        this.foodStallRepository = foodStallRepository;
    }

    @Override
    public UserFoodStallDto findWith(Long id) {
        return foodStallRepository
                .findByIdAndUserStatusNot(id, UserStatus.INACTIVE)
                .map(UserMapper::mapToUserFoodStallDto)
                .orElseThrow(() -> new FoodStallNotFoundException("Food stall with id " + id + " not found"));
    }
}
