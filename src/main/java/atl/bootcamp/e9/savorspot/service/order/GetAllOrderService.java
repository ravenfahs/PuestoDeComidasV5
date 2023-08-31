package atl.bootcamp.e9.savorspot.service.order;

import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderDTO;

import java.util.List;

public interface GetAllOrderService {

    List<OrderDTO> getAllOrder(Long userID);
}
