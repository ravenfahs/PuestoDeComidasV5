package com.restaurante.reviews.service.impl;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.Order;
import com.restaurante.reviews.models.OrderStatus;
import com.restaurante.reviews.models.User;
import com.restaurante.reviews.models.UserType;
import com.restaurante.reviews.repository.ClientRepository;
import com.restaurante.reviews.repository.FoodStellRepository;
import com.restaurante.reviews.repository.OrderFoodsRepository;
import com.restaurante.reviews.repository.OrderRepository;
import com.restaurante.reviews.service.GetAllOrderByStatusService;
import com.restaurante.reviews.service.impl.util.ListOrdersService;
import com.restaurante.reviews.service.impl.util.ValidateUser;

import java.util.List;

public class GetAllOrderByStatusServiceImpl implements GetAllOrderByStatusService {

    private final OrderRepository orderRepository;
    private final ListOrdersService listOrdersService;
    private final FoodStellRepository foodStallRepository;
   private final  ClientRepository clientRepository;

    public GetAllOrderByStatusServiceImpl(OrderRepository orderRepository,
                                                              OrderFoodsRepository orderFoodsRepository,
                                                              FoodStellRepository foodStallRepository,
                                                              ClientRepository clientRepository) {

        this.orderRepository = orderRepository;
        this.listOrdersService = new ListOrdersService(orderFoodsRepository);
        this.foodStallRepository = foodStallRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<OrderDTO> getAllOrderByStatus(Long userID, OrderStatus orderStatus) {

        User user = ValidateUser.userType(userID,foodStallRepository, clientRepository);

        List<Order> modelOrderbyStatus;

        if(user.getUserType().equals(UserType.FOOD_STALL)) {
            modelOrderbyStatus = orderRepository.findOrdersByFoodStall_IdAndState(userID, orderStatus);
        }else{
            modelOrderbyStatus = orderRepository.findOrdersByClient_IdAndState(userID, orderStatus);
        }

        return listOrdersService.listOrder(modelOrderbyStatus);
    }
}
