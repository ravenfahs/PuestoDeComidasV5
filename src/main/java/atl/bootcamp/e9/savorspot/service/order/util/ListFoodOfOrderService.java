package atl.bootcamp.e9.savorspot.service.order.util;

import atl.bootcamp.e9.savorspot.models.OrderFoods;
import atl.bootcamp.e9.savorspot.repository.OrderFoodsRepository;
import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderFoodsDTO;
import atl.bootcamp.e9.savorspot.service.order.mappers.MapperOrdenFoods;

import java.util.ArrayList;
import java.util.List;

public class ListFoodOfOrderService {

    private final OrderFoodsRepository orderFoodsRepository;

    public ListFoodOfOrderService(OrderFoodsRepository orderFoodsRepository) {
        this.orderFoodsRepository = orderFoodsRepository;
    }

    public  List<OrderFoodsDTO> listFoods(Long id) {

        List<OrderFoods> modelOrderFoods = orderFoodsRepository.findByOrder_Id(id);
        List<OrderFoodsDTO> listFoods = new ArrayList<>();

        modelOrderFoods.forEach(
                orderFoods ->
                        listFoods.add(
                                MapperOrdenFoods.mapToOrderFoodDTO(orderFoods)
                        )
        );
        
        return listFoods;
    }
}
