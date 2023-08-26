package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.OrderRequestDTO;
import com.restaurante.reviews.exceptions.NotPermitsUserException;
import com.restaurante.reviews.exceptions.UserNotFoundException;
import com.restaurante.reviews.util.MapperOrden;
import com.restaurante.reviews.models.*;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.service.CreateOrderFoodService;
import com.restaurante.reviews.service.CreateOrderService;
import com.restaurante.reviews.service.impl.util.ValidateUser;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final FoodStallRepository foodStallRepository;
    private final CreateOrderFoodService createOrdenFoodServiceImpl;


    public CreateOrderServiceImpl(OrderRepository orderRepository,
                                  ClientRepository clientRepository,
                                  FoodsRepository foodRepository,
                                  FoodStallRepository foodStallRepository,
                                  OrderFoodsRepository orderFoodRepository) {

        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.foodStallRepository = foodStallRepository;
        createOrdenFoodServiceImpl = new CreateOrderFoodServiceImpl(foodRepository, orderFoodRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<String> createOrden(Long clientID,OrderRequestDTO orderRequestDTO) {

        User user = ValidateUser.userType(clientID, foodStallRepository, clientRepository);

        if (!(user.getUserType().equals(UserType.CLIENT))){
            throw new NotPermitsUserException("You don't have permits, you need to be a client");
        }

        Client client = clientRepository.findById(clientID)
                .orElseThrow(() -> new UserNotFoundException("Client not found with ID: "+clientID));

        FoodStall foodStall = foodStallRepository.findById(orderRequestDTO.getIdFoodStall())
                .orElseThrow(() -> new UserNotFoundException("Food Stall not found with ID: "+ orderRequestDTO.getIdFoodStall()));

        Order newOrden;

        newOrden = orderRepository.save(
                MapperOrden.mapToOrden(orderRequestDTO, client, foodStall)
        );
        createOrdenFoodServiceImpl.createOrdenFoods(orderRequestDTO.getFoods(),newOrden);
        orderRepository.save(
                MapperOrden.mapOrderTotal(newOrden, createOrdenFoodServiceImpl.getTotal())
        );

        return new ResponseEntity<>("Order successfully created.", HttpStatus.CREATED);
    }
}
