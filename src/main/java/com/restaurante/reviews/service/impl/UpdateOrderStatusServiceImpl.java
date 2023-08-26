package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.exceptions.NotPermitsUserException;
import com.restaurante.reviews.exceptions.OrderNotFoundException;
import com.restaurante.reviews.util.MapperOrden;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.models.OrderStatus;
import com.restaurante.reviews.models.User;
import com.restaurante.reviews.models.UserType;
import com.restaurante.reviews.repository.ClientRepository;
import com.restaurante.reviews.repository.FoodStallRepository;
import com.restaurante.reviews.repository.OrderRepository;
import com.restaurante.reviews.service.UpdateOrderStatusService;
import com.restaurante.reviews.service.impl.util.ValidateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusServiceImpl implements UpdateOrderStatusService {

    private final OrderRepository orderRepository;
    private final  FoodStallRepository foodStallRepository;
    private final ClientRepository clientRepository;

    public UpdateOrderStatusServiceImpl(OrderRepository orderRepository,
                                                            FoodStallRepository foodStallRepository,
                                                            ClientRepository clientRepository) {

        this.orderRepository = orderRepository;
        this.foodStallRepository = foodStallRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<String> updateOrder(Long userID, Long orderID) {

        Order order;
        User user = ValidateUser.userType(userID, foodStallRepository, clientRepository);

        if (user.getUserType().equals(UserType.FOOD_STALL)) {

            order = orderRepository.findByIdAndStateNot(orderID, OrderStatus.CANCELED)
                    .orElseThrow(() ->
                            new OrderNotFoundException("It is not possible to perform this action for Order with ID: " + orderID+
                                                                         " because it has CANCELED status."));

            orderRepository.save(MapperOrden.mapToUpdateOrder(order));

            return ResponseEntity.ok("Order successfully update");
        }

        throw new NotPermitsUserException("You don't have permits, you need to be a food stand");
    }
}
