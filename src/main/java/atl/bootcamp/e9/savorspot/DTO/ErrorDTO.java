package atl.bootcamp.e9.savorspot.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private LocalDateTime localDateTime;
    private int status;
    private String error;
    private String messaje;
    private String path;
}
