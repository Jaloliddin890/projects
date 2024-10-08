package tmrv.dev.springprofilingfreemakerlogging.model;

import java.io.Serializable;

/**
 * DTO for {@link AuthUser}
 */
public record UserDto(String username, String password, String phoneNumber) implements Serializable {
  }