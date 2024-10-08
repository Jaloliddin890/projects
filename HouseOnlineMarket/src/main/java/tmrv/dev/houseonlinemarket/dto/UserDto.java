package tmrv.dev.houseonlinemarket.dto;

import tmrv.dev.houseonlinemarket.entities.domains.Role;

import java.io.Serializable;

/**
 * DTO for {@link tmrv.dev.houseonlinemarket.entities.User}
 */
public record UserDto(String username, String password, Role role,
                      boolean isActive) implements Serializable {
}