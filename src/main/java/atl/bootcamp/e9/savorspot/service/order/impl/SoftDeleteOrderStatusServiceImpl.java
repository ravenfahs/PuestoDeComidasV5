package atl.bootcamp.e9.savorspot.service.order.impl;

import atl.bootcamp.e9.savorspot.service.order.mappers.MapperOrden;
import atl.bootcamp.e9.savorspot.exceptions.OrderNotFoundException;
import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.models.OrderStatus;
import atl.bootcamp.e9.savorspot.repository.OrderRepository;
import atl.bootcamp.e9.savorspot.service.order.SoftDeleteOrderStatusService;
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
                ()-> new OrderNotFoundException("It is not possible to perform this action for Order with ID " + orderID+
                                                                    " because it has COMPLETE status."));

        orderRepository.save(
                MapperOrden.mapToSoftDeleteOrder(order)
        );

        return ResponseEntity.ok("Order successfully deleted");
    }
}
