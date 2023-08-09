package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.service.GetAllOrderService;
import com.restaurante.reviews.service.impl.util.ListOrdersService;
import com.restaurante.reviews.service.impl.util.ValidateUserType;

import java.util.List;

public class GetAllOrderServiceImpl implements GetAllOrderService {
    private final OrderRepository orderRepository;
    private final ListOrdersService listOrdersService;

    public GetAllOrderServiceImpl(OrderRepository orderRepository, OrderFoodsRepository orderFoodsRepository) {
        this.orderRepository = orderRepository;
        this.listOrdersService = new ListOrdersService(orderFoodsRepository);
    }

    @Override
    public List<OrderDTO> getAllOrder(Long id, FoodStellRepository foodStallRepository,
                                      ClientRepository clientRepository) {

        List<Order> modelOrder = ValidateUserType.User(id,foodStallRepository, clientRepository, orderRepository);
        return listOrdersService.listOrder(modelOrder);
    }
}
