package atl.bootcamp.e9.savorspot.service.order.impl;

import atl.bootcamp.e9.savorspot.repository.OrderFoodsRepository;
import atl.bootcamp.e9.savorspot.service.order.util.ListOrdersService;
import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderDTO;
import atl.bootcamp.e9.savorspot.exceptions.NotPermitsUserException;
import atl.bootcamp.e9.savorspot.exceptions.OrderNotFoundException;
import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.repository.OrderRepository;
import atl.bootcamp.e9.savorspot.service.order.GetOrderByIdService;
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
