package atl.bootcamp.e9.savorspot.service.order.impl;

import atl.bootcamp.e9.savorspot.models.UserType;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.repository.OrderFoodsRepository;
import atl.bootcamp.e9.savorspot.service.order.util.ListOrdersService;
import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderDTO;
import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.models.OrderStatus;
import atl.bootcamp.e9.savorspot.models.User;
import atl.bootcamp.e9.savorspot.repository.ClientRepository;
import atl.bootcamp.e9.savorspot.repository.OrderRepository;
import atl.bootcamp.e9.savorspot.service.order.GetAllOrderByStatusService;
import atl.bootcamp.e9.savorspot.service.order.util.ValidateUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllOrderByStatusServiceImpl implements GetAllOrderByStatusService {

    private final OrderRepository orderRepository;
    private final ListOrdersService listOrdersService;
    private final FoodStallRepository foodStallRepository;
   private final  ClientRepository clientRepository;

    public GetAllOrderByStatusServiceImpl(OrderRepository orderRepository,
                                          OrderFoodsRepository orderFoodsRepository,
                                          FoodStallRepository foodStallRepository,
                                          ClientRepository clientRepository) {

        this.orderRepository = orderRepository;
        this.listOrdersService = new ListOrdersService(orderFoodsRepository);
        this.foodStallRepository = foodStallRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<OrderDTO> getAllOrderByStatus(Long userID, OrderStatus orderStatus) {

        User user = ValidateUser.userType(userID,foodStallRepository, clientRepository);

        List<Order> modelOrderStatus;

        if(user.getUserType().equals(UserType.FOOD_STALL)) {
            modelOrderStatus = orderRepository.findOrdersByFoodStall_IdAndState(userID, orderStatus);
        }else{
            modelOrderStatus = orderRepository.findOrdersByClient_IdAndState(userID, orderStatus);
        }

        return listOrdersService.listOrder(modelOrderStatus);
    }
}
