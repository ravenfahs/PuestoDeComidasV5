package atl.bootcamp.e9.savorspot.service.order.impl;

import atl.bootcamp.e9.savorspot.models.UserType;
import atl.bootcamp.e9.savorspot.repository.ClientRepository;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.repository.OrderFoodsRepository;
import atl.bootcamp.e9.savorspot.repository.OrderRepository;
import atl.bootcamp.e9.savorspot.service.order.util.ListOrdersService;
import atl.bootcamp.e9.savorspot.service.order.util.ValidateUser;
import atl.bootcamp.e9.savorspot.DTO.OrderDTO;
import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.models.User;
import atl.bootcamp.e9.savorspot.service.order.GetAllOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllOrderServiceImpl implements GetAllOrderService {
    private final OrderRepository orderRepository;
    private final ListOrdersService listOrdersService;

    private final FoodStallRepository foodStallRepository;
    private final ClientRepository clientRepository;

    public GetAllOrderServiceImpl(OrderRepository orderRepository,
                                                  OrderFoodsRepository orderFoodsRepository,
                                                  FoodStallRepository foodStallRepository,
                                                  ClientRepository clientRepository) {

        this.orderRepository = orderRepository;
        this.listOrdersService = new ListOrdersService(orderFoodsRepository);
        this.foodStallRepository = foodStallRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<OrderDTO> getAllOrder(Long userID) {

        User user = ValidateUser.userType(userID,foodStallRepository, clientRepository);
        List<Order> modelOrder;

        if(user.getUserType().equals(UserType.FOOD_STALL)) {
            modelOrder = orderRepository.findOrdersByFoodStall_Id(user.getId());
        }else{
            modelOrder = orderRepository.findOrdersByClient_Id(user.getId());
        }

        return listOrdersService.listOrder(modelOrder);
    }
}
