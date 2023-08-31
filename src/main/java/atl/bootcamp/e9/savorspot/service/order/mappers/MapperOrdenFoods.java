package atl.bootcamp.e9.savorspot.service.order.mappers;

import atl.bootcamp.e9.savorspot.DTO.OrderDTO.FoodOnOrderDTO;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.models.OrderFoods;
import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderFoodsDTO;
import atl.bootcamp.e9.savorspot.models.Order;


public final class MapperOrdenFoods {

    private MapperOrdenFoods() {
        throw new UnsupportedOperationException("A static class cannot be instantiated");
    }

    public static OrderFoods mapToOrdenFood(Foods food,
                                            Order newOrden,
                                            FoodOnOrderDTO foodOnOrderDTO) {

        OrderFoods orderFoods = new OrderFoods();

        orderFoods.setOrder(newOrden);
        orderFoods.setFood(food);
        orderFoods.setQuantity(foodOnOrderDTO.getQuantity());

        return orderFoods;
    }

    public static OrderFoodsDTO mapToOrderFoodDTO(OrderFoods orderFoods){

        return new OrderFoodsDTO(
                                    orderFoods.getFood().getName(),
                                    orderFoods.getFood().getDescription(),
                                    orderFoods.getQuantity(),
                                    orderFoods.getFood().getPrice());
    }
}
