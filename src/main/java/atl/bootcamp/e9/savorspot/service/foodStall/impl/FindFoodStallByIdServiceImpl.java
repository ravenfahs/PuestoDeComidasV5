package atl.bootcamp.e9.savorspot.service.foodStall.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.FoodStallDto;
import atl.bootcamp.e9.savorspot.exceptions.FoodStallNotFoundException;
import atl.bootcamp.e9.savorspot.models.UserStatus;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.service.foodStall.FindFoodStallByIdService;
import atl.bootcamp.e9.savorspot.service.foodStall.util.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class FindFoodStallByIdServiceImpl implements FindFoodStallByIdService {

    private final FoodStallRepository foodStallRepository;

    public FindFoodStallByIdServiceImpl(FoodStallRepository foodStallRepository) {
        this.foodStallRepository = foodStallRepository;
    }

    @Override
    public FoodStallDto findWith(Long id) {
        return foodStallRepository
                .findByIdAndUserStatusNot(id, UserStatus.INACTIVE)
                .map(UserMapper::mapToFoodStallDto)
                .orElseThrow(() -> new FoodStallNotFoundException("Food stall with id " + id + " not found"));
    }
}
