package atl.bootcamp.e9.savorspot.service.order.impl;

import atl.bootcamp.e9.savorspot.models.*;
import atl.bootcamp.e9.savorspot.repository.*;
import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderRequestDTO;
import atl.bootcamp.e9.savorspot.exceptions.NotPermitsUserException;
import atl.bootcamp.e9.savorspot.exceptions.UserNotFoundException;
import atl.bootcamp.e9.savorspot.service.order.util.ValidateUser;
import atl.bootcamp.e9.savorspot.service.order.mappers.MapperOrden;
import atl.bootcamp.e9.savorspot.service.order.CreateOrderFoodService;
import atl.bootcamp.e9.savorspot.service.order.CreateOrderService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final FoodStallRepository foodStallRepository;
    private final CreateOrderFoodService createOrdenFoodServiceImpl;


    public CreateOrderServiceImpl(OrderRepository orderRepository,
                                  ClientRepository clientRepository,
                                  FoodsRepository foodRepository,
                                  FoodStallRepository foodStallRepository,
                                  OrderFoodsRepository orderFoodRepository) {

        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.foodStallRepository = foodStallRepository;
        createOrdenFoodServiceImpl = new CreateOrderFoodServiceImpl(foodRepository, orderFoodRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<String> createOrden(Long clientID,OrderRequestDTO orderRequestDTO) {

        User user = ValidateUser.userType(clientID, foodStallRepository, clientRepository);

        if (!(user.getUserType().equals(UserType.CLIENT))){
            throw new NotPermitsUserException("You don't have permits, you need to be a client");
        }

        Client client = clientRepository.findById(clientID)
                .orElseThrow(() -> new UserNotFoundException("Client not found with ID: "+clientID));

        FoodStall foodStall = foodStallRepository.findById(orderRequestDTO.getIdFoodStall())
                .orElseThrow(() -> new UserNotFoundException("Food Stall not found with ID: "+ orderRequestDTO.getIdFoodStall()));

        Order newOrden;

        newOrden = orderRepository.save(
                MapperOrden.mapToOrden(orderRequestDTO, client, foodStall)
        );
        createOrdenFoodServiceImpl.createOrdenFoods(orderRequestDTO.getFoods(),newOrden);
        orderRepository.save(
                MapperOrden.mapOrderTotal(newOrden, createOrdenFoodServiceImpl.getTotal())
        );

        return new ResponseEntity<>("Order successfully created.", HttpStatus.CREATED);
    }
}
