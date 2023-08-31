package atl.bootcamp.e9.savorspot.DTO.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodOnOrderDTO {
    private Long idFood;
    private int quantity;
}
