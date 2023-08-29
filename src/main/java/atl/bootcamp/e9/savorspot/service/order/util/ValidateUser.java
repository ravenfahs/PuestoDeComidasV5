package atl.bootcamp.e9.savorspot.service.order.util;

import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.exceptions.UserNotFoundException;
import atl.bootcamp.e9.savorspot.models.User;
import atl.bootcamp.e9.savorspot.repository.ClientRepository;


public final class ValidateUser {

    public static User userType(Long id,
                                FoodStallRepository foodStallRepository,
                                ClientRepository clientRepository){

        return (foodStallRepository.findById(id).isPresent())?
                            foodStallRepository.findById(id).get() :
                            clientRepository.findById(id)
                                    .orElseThrow(() -> new UserNotFoundException("User with ID: "+ id + "not found"));

    }
}
