package uz.pdp.domains;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthRoles {

    private Long id;
    private String name;
    private String code;
    private List<AuthPermission> permissions;
}
