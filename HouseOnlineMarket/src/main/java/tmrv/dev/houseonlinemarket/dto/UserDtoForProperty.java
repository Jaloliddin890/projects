package tmrv.dev.houseonlinemarket.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link tmrv.dev.houseonlinemarket.entities.User}
 */
public record UserDtoForProperty(Long userId, @NotNull String username) implements Serializable {
}