package atl.bootcamp.e9.savorspot.service.order;

import atl.bootcamp.e9.savorspot.DTO.OrderDTO.OrderDTO;

public interface GetOrderByIdService {
    OrderDTO getOrderbyId(Long id, Long userID);
}
