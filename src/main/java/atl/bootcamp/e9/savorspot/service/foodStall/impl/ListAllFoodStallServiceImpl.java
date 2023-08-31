package atl.bootcamp.e9.savorspot.service.foodStall.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.FoodStallDto;
import atl.bootcamp.e9.savorspot.models.UserStatus;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.service.foodStall.ListAllFoodStallService;
import atl.bootcamp.e9.savorspot.service.foodStall.util.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllFoodStallServiceImpl implements ListAllFoodStallService {

    private final FoodStallRepository foodStallRepository;

    public ListAllFoodStallServiceImpl(FoodStallRepository foodStallRepository) {
        this.foodStallRepository = foodStallRepository;
    }

    @Override
    public Page<FoodStallDto> list(Pageable pageable) {
        return foodStallRepository.findByUserStatusNot(UserStatus.INACTIVE, pageable).map(UserMapper::mapToFoodStallDto);
    }
}
