package com.restaurante.reviews.service.impl.util;

import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.models.User;
import com.restaurante.reviews.repository.ClientRepository;
import com.restaurante.reviews.repository.FoodStellRepository;
import com.restaurante.reviews.repository.OrderRepository;


import java.util.List;


public final class ValidateUserType {

    public static List<Order> User(Long id,
                                   FoodStellRepository foodStallRepository,
                                   ClientRepository clientRepository,
                                   OrderRepository orderRepository){

      User user = (foodStallRepository.findById(id).isPresent())?
                            foodStallRepository.findById(id).get() :
                            clientRepository.findById(id).get();

        if(user.getUserType().toString().equals("FOOD_STALL")) {
            return orderRepository.findOrdersByFoodStall_Id(user.getId());
        }else{
            return orderRepository.findOrdersByClient_Id(user.getId());
        }

    }
}
