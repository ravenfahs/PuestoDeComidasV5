package atl.bootcamp.e9.savorspot.DTO.OrderDTO;

import atl.bootcamp.e9.savorspot.DTO.ClientDTO.ClientDTOBasico;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderDTO {
    private Long id;
    private String state;
    private LocalDateTime dateTime;
    private String timeDelivery;
    private double total;
    private ClientDTOBasico client;
    private Long foodStallId;
    @Setter
    private List<OrderFoodsDTO> foods;

    public OrderDTO(Long id, String state, LocalDateTime dateTime, String timeDelivery, double total, ClientDTOBasico client, Long foodStallId) {
        this.id = id;
        this.state = state;
        this.dateTime = dateTime;
        this.timeDelivery = timeDelivery;
        this.total = total;
        this.client = client;
        this.foodStallId = foodStallId;
    }
}
