package atl.bootcamp.e9.savorspot.repository;

import atl.bootcamp.e9.savorspot.models.Order;
import atl.bootcamp.e9.savorspot.models.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order> findOrdersByClient_Id(Long userID);
    List<Order> findOrdersByFoodStall_Id(Long userID);
    List<Order> findOrdersByClient_IdAndState(Long userID, OrderStatus orderStatus);
    List<Order> findOrdersByFoodStall_IdAndState(Long userID, OrderStatus orderStatus);
    Optional<Order> findByIdAndStateNot(Long orderID, OrderStatus orderStatus);
}
