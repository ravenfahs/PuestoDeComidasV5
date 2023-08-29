package atl.bootcamp.e9.savorspot.service.order.mappers;

import atl.bootcamp.e9.savorspot.DTO.ClientDTOBasico;
import atl.bootcamp.e9.savorspot.DTO.OrderRequestDTO;
import atl.bootcamp.e9.savorspot.DTO.OrderDTO;
import atl.bootcamp.e9.savorspot.DTO.OrderFoodsDTO;
import atl.bootcamp.e9.savorspot.models.Client;
import atl.bootcamp.e9.savorspot.models.FoodStall;
import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.models.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public final class MapperOrden {

    private MapperOrden() {
        throw new UnsupportedOperationException("A static class cannot be instantiated");
    }

    public static Order mapToOrden(OrderRequestDTO orderRequestDTO, Client client, FoodStall foodStall){

        Order newOrden = new Order();

        newOrden.setClient(client);
        newOrden.setState(OrderStatus.ACTIVE);
        newOrden.setDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()) );
        newOrden.setTimeDelivery(orderRequestDTO.getTimeDelivery());
        newOrden.setFoodStall(foodStall);

        return newOrden;
    }

    public static Order mapOrderTotal(Order order, double total) {
        order.setTotal(total);
        return order;
    }

    public static OrderDTO mapToOrderDTO(Order order){

         return new OrderDTO(
                 order.getId(),
                order.getState().toString(),
                order.getDateTime(),
                order.getTimeDelivery(),
                order.getTotal(),
                new ClientDTOBasico(
                        order.getClient().getId(),
                        order.getClient().getFullName(),
                        order.getClient().getUserStatus().toString(),
                        order.getClient().getPhone()
                ),
                 order.getFoodStall().getId()
         );
    }

    public static OrderDTO mapFoodsOfOrderDTO(OrderDTO orderDTO, List<OrderFoodsDTO> allFoods) {

        orderDTO.setFoods(allFoods);

        return orderDTO;
    }

    public static Order mapToUpdateOrder(Order order){

        order.setState(OrderStatus.COMPLETE);
        return order;
    }

    public static Order mapToSoftDeleteOrder(Order order){

        order.setState(OrderStatus.CANCELED);
        return order;
    }
}
