package atl.bootcamp.e9.savorspot.service.order;

import atl.bootcamp.e9.savorspot.DTO.OrderDTO.FoodOnOrderDTO;
import atl.bootcamp.e9.savorspot.models.Order;

import java.util.List;

public interface CreateOrderFoodService {

    void createOrdenFoods(List<FoodOnOrderDTO> listFoodOnOrderDTO, Order newOrden);
    double getTotal();
}
