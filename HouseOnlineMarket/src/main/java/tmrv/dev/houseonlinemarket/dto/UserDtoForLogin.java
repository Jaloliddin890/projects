package tmrv.dev.houseonlinemarket.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link tmrv.dev.houseonlinemarket.entities.User}
 */
public record UserDtoForLogin(@NotEmpty String username, @NotNull String password) implements Serializable {
}