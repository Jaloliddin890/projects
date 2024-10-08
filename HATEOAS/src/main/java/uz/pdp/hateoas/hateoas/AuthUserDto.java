package uz.pdp.hateoas.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link AuthUser}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto implements Serializable {
    private String username;
    private String password;
}