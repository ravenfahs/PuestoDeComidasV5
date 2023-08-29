package com.restaurante.reviews.service.order.util;

import com.restaurante.reviews.exceptions.UserNotFoundException;
import com.restaurante.reviews.models.User;
import com.restaurante.reviews.repository.ClientRepository;
import com.restaurante.reviews.repository.FoodStallRepository;


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
