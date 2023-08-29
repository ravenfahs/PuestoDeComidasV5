package atl.bootcamp.e9.savorspot.service.order.impl;

import atl.bootcamp.e9.savorspot.exceptions.FoodNotFoundException;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.repository.OrderFoodsRepository;
import atl.bootcamp.e9.savorspot.DTO.FoodOnOrderDTO;
import atl.bootcamp.e9.savorspot.service.order.mappers.MapperOrdenFoods;
import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.repository.FoodsRepository;
import atl.bootcamp.e9.savorspot.service.order.CreateOrderFoodService;

import java.util.List;

public class CreateOrderFoodServiceImpl implements CreateOrderFoodService {

    private final FoodsRepository foodRepository;
    private final OrderFoodsRepository orderFoodsRepository;
    private double totalValueFoods;

    public CreateOrderFoodServiceImpl(FoodsRepository foodRepository,
                                      OrderFoodsRepository orderFoodsRepository) {

        this.foodRepository = foodRepository;
        this.orderFoodsRepository = orderFoodsRepository;
    }

    @Override
    public void createOrdenFoods(List<FoodOnOrderDTO> listFoodOnOrderDTO, Order newOrden){

        totalValueFoods = 0;

         listFoodOnOrderDTO.forEach(
                (FoodOnOrderDTO foodOnOrderDTO) -> {

                    Foods food = foodRepository.findById(foodOnOrderDTO.getIdFood())
                            .orElseThrow(()-> new FoodNotFoundException("Food not found with ID: "+ foodOnOrderDTO.getIdFood()));

                    orderFoodsRepository.save(
                            MapperOrdenFoods.mapToOrdenFood(food, newOrden, foodOnOrderDTO)
                    );

                    totalValueFoods += food.getPrice() * foodOnOrderDTO.getQuantity();
                }
        );
    }

    @Override
    public double getTotal() {
        return totalValueFoods ;
    }


}
