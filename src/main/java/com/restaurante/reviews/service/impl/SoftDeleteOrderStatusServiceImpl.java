package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.exceptions.OrderNotFoundException;
import com.restaurante.reviews.mappers.MapperOrden;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.models.OrderStatus;
import com.restaurante.reviews.repository.OrderRepository;
import com.restaurante.reviews.service.SoftDeleteOrderStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SoftDeleteOrderStatusServiceImpl implements SoftDeleteOrderStatusService {

    private final OrderRepository orderRepository;
    public SoftDeleteOrderStatusServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<String> softDeleteOrder(Long orderID) {

        Order order = orderRepository.findByIdAndStateNot(orderID, OrderStatus.COMPLETE).orElseThrow(
                ()-> new OrderNotFoundException("It is not possible to perform this action for Order with ID " + orderID));

        orderRepository.save(
                MapperOrden.mapToSoftDeleteOrder(order)
        );

        return ResponseEntity.ok("Order successfully deleted");
    }
}
