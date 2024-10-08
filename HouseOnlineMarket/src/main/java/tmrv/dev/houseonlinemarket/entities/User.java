package tmrv.dev.houseonlinemarket.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tmrv.dev.houseonlinemarket.entities.domains.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isActive;
}
