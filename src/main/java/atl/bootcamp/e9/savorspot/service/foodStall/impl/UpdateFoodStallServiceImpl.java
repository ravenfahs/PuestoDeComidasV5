package atl.bootcamp.e9.savorspot.service.foodStall.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.UserFoodStallDto;
import atl.bootcamp.e9.savorspot.exceptions.FoodStallNotFoundException;
import atl.bootcamp.e9.savorspot.models.FoodStall;
import atl.bootcamp.e9.savorspot.models.UserStatus;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.service.foodStall.UpdateFoodStallService;
import atl.bootcamp.e9.savorspot.service.foodStall.util.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateFoodStallServiceImpl implements UpdateFoodStallService {

    private final FoodStallRepository foodStallRepository;

    public UpdateFoodStallServiceImpl(FoodStallRepository foodStallRepository) {
        this.foodStallRepository = foodStallRepository;
    }

    @Override
    public void update(UserFoodStallDto userFoodStallDto) {

        FoodStall foodStall = foodStallRepository
                .findByIdAndUserStatusNot(userFoodStallDto.id(), UserStatus.INACTIVE)
                .orElseThrow(() -> new FoodStallNotFoundException("Food stall with id " + userFoodStallDto.id() + " not found"));

        foodStallRepository.save(UserMapper.mapToFoodStallToUpdate(userFoodStallDto, foodStall));
    }
}
