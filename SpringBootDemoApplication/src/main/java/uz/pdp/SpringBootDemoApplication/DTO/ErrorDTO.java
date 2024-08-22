package uz.pdp.SpringBootDemoApplication.DTO;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {

    private String errorPath;
    private String errorCode;
    private String errorBody;

    @Builder.Default
    private LocalDateTime timestamp= LocalDateTime.now();
}
