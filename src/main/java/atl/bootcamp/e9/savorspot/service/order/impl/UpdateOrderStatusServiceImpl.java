package atl.bootcamp.e9.savorspot.service.order.impl;

import atl.bootcamp.e9.savorspot.models.UserType;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.exceptions.NotPermitsUserException;
import atl.bootcamp.e9.savorspot.exceptions.OrderNotFoundException;
import atl.bootcamp.e9.savorspot.service.order.util.ValidateUser;
import atl.bootcamp.e9.savorspot.service.order.mappers.MapperOrden;
import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.models.OrderStatus;
import atl.bootcamp.e9.savorspot.models.User;
import atl.bootcamp.e9.savorspot.repository.ClientRepository;
import atl.bootcamp.e9.savorspot.repository.OrderRepository;
import atl.bootcamp.e9.savorspot.service.order.UpdateOrderStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusServiceImpl implements UpdateOrderStatusService {

    private final OrderRepository orderRepository;
    private final FoodStallRepository foodStallRepository;
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
