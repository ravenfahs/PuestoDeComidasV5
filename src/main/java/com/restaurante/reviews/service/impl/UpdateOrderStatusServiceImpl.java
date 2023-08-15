package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.exceptions.NotPermitsUserException;
import com.restaurante.reviews.exceptions.OrderNotFoundException;
import com.restaurante.reviews.mappers.MapperOrden;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.models.OrderStatus;
import com.restaurante.reviews.models.User;
import com.restaurante.reviews.repository.ClientRepository;
import com.restaurante.reviews.repository.FoodStellRepository;
import com.restaurante.reviews.repository.OrderRepository;
import com.restaurante.reviews.service.UpdateOrderStatusService;
import com.restaurante.reviews.service.impl.util.ValidateUserType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusServiceImpl implements UpdateOrderStatusService {

    private final OrderRepository orderRepository;

    public UpdateOrderStatusServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<String> updateOrder(Long userID, Long id,
                                                                  FoodStellRepository foodStallRepository,
                                                                  ClientRepository clientRepository) {

        Order order;
        User user = ValidateUserType.userType(userID, foodStallRepository, clientRepository);

        if (user.getUserType().toString().equals("FOOD_STALL")) {

            order = orderRepository.findByIdAndStateNot(id, OrderStatus.CANCELED)
                    .orElseThrow(() ->
                            new OrderNotFoundException("It is not possible to perform this action for Order with ID " + id));

            orderRepository.save(MapperOrden.mapToUpdateOrder(order));

            return ResponseEntity.ok("Order created successfully");
        }

        throw new NotPermitsUserException("You don't have permits, you need to be a food stand");
    }
}
