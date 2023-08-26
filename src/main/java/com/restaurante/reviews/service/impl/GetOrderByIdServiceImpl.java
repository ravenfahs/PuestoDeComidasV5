package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.exceptions.NotPermitsUserException;
import com.restaurante.reviews.exceptions.OrderNotFoundException;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.OrderFoodsRepository;
import com.restaurante.reviews.repository.OrderRepository;
import com.restaurante.reviews.service.GetOrderByIdService;
import com.restaurante.reviews.service.impl.util.ListOrdersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetOrderByIdServiceImpl implements GetOrderByIdService {

    private final OrderRepository ordenRepository;
    private final ListOrdersService listOrdersService;

    public GetOrderByIdServiceImpl(OrderRepository orderRepository,
                                                    OrderFoodsRepository orderFoodsRepository) {

        this.ordenRepository = orderRepository;
        listOrdersService = new ListOrdersService(orderFoodsRepository);
    }

    @Override
    public OrderDTO getOrderbyId(Long orderID, Long userID) {

        List<Order> modelOrder = new ArrayList<>();

        Order order = ordenRepository.findById(orderID)
                .orElseThrow(()-> new OrderNotFoundException("Order with ID: "+orderID+" not found"));


        if(!(order.getFoodStall().getId().equals(userID)  || order.getClient().getId().equals(userID))){
            throw new NotPermitsUserException("It is not possible to perform this action for Order with ID :" + orderID);
        }

        modelOrder.add(order);
        return listOrdersService.listOrder(modelOrder).get(0);
    }
}
