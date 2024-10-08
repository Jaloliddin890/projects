package com.tmrv.SecurityJWT.model;

import java.io.Serializable;

/**
 * DTO for {@link com.tmrv.SecurityJWT.model.User}
 */
public record UserDto(String firstname, String lastname, String username, String password,
                      Role role) implements Serializable {
}