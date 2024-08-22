package uz.pdp.SpringBootDemoApplication.Domains;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Store {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String desc;


}
