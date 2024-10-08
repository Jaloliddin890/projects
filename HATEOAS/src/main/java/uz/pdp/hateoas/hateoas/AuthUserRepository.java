package uz.pdp.hateoas.hateoas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> getUserByUsername(String username);
    Optional<AuthUser> getUserById(Long id);
}
