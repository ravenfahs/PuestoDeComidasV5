package atl.bootcamp.e9.savorspot.DTO.ClientDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientDTOBasico {

    private Long id;
    private String fullName;
    private String status;
    private String phone;
}
