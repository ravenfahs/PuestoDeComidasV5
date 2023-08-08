package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.OrderFoodsRepository;
import com.restaurante.reviews.repository.OrderRepository;
import com.restaurante.reviews.service.GetOrderByIdService;
import com.restaurante.reviews.service.impl.util.ListOrdersService;

import java.util.ArrayList;
import java.util.List;

public class GetOrderByIdServiceImpl implements GetOrderByIdService {

    private final OrderRepository ordenRepository;
    private ListOrdersService listOrdersService;

    public GetOrderByIdServiceImpl(OrderRepository orderRepository, OrderFoodsRepository orderFoodsRepository) {
        this.ordenRepository = orderRepository;
        listOrdersService = new ListOrdersService(orderFoodsRepository);
    }

    @Override
    public OrderDTO getOrderbyId(Long id) {

        List<Order> modelOrder = new ArrayList<>();
        modelOrder.add(ordenRepository.findById(id).orElse(null));
        return listOrdersService.listOrder(modelOrder).get(0);
    }
}
