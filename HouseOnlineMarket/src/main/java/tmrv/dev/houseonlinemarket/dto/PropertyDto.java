package tmrv.dev.houseonlinemarket.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import tmrv.dev.houseonlinemarket.entities.domains.Type;

import java.io.Serializable;

/**
 * DTO for {@link tmrv.dev.houseonlinemarket.entities.Property}
 */
public record PropertyDto(
        @NotEmpty String title,
        String description,
        double price,
        String location,
        Type type,
        boolean available,
        MultipartFile imageFile, UserDtoForProperty userDtoForProperty) implements Serializable {
}