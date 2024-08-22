package uz.pdp.authuser;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthUser {

    private Long id;

    private String username;

    private String password;

    private AuthRole role;


    public enum AuthRole {
        ADMIN,
        USER
    }
}
