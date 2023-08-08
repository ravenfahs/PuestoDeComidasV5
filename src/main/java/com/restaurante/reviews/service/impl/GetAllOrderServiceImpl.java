package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.repository.OrderFoodsRepository;
import com.restaurante.reviews.repository.OrderRepository;
import com.restaurante.reviews.service.GetAllOrderService;
import com.restaurante.reviews.service.impl.util.ListOrdersService;

import java.util.List;

public class GetAllOrderServiceImpl implements GetAllOrderService {
    private final OrderRepository orderRepository;
    private final ListOrdersService listOrdersService;

    public GetAllOrderServiceImpl(OrderRepository orderRepository, OrderFoodsRepository orderFoodsRepository) {
        this.orderRepository = orderRepository;
        this.listOrdersService = new ListOrdersService(orderFoodsRepository);
    }

    @Override
    public List<OrderDTO> getAllOrder() {

            List<Order> modelOrder = (List<Order>) orderRepository.findAll();
            return listOrdersService.listOrder(modelOrder);
    }
}
