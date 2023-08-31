package atl.bootcamp.e9.savorspot.service.order;

import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderDTO;
import atl.bootcamp.e9.savorspot.models.OrderStatus;

import java.util.List;

public interface GetAllOrderByStatusService {

    List<OrderDTO> getAllOrderByStatus(Long userID, OrderStatus orderStatus);
}
