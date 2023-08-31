package atl.bootcamp.e9.savorspot.service.foodStall.impl;

import atl.bootcamp.e9.savorspot.DTO.FoodStallDTO.RegisterUserFoodStallDto;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.service.foodStall.RegisterFoodStallService;
import atl.bootcamp.e9.savorspot.service.foodStall.util.UserMapper;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

@Service
public class RegisterFoodStallServiceImpl implements RegisterFoodStallService {

    private final FoodStallRepository foodStallRepository;

    public RegisterFoodStallServiceImpl(FoodStallRepository foodStallRepository) {
        this.foodStallRepository = foodStallRepository;
    }

    @Override
    public void register(RegisterUserFoodStallDto registerUserFoodStallDto) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        String passwordHash = argon2.hash(1, 1024, 1, registerUserFoodStallDto.password());

        foodStallRepository.save(UserMapper.mapToFoodStallToRegister(registerUserFoodStallDto, passwordHash));
    }
}