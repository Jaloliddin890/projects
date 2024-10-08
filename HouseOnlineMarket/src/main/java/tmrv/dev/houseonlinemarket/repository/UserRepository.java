package tmrv.dev.houseonlinemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tmrv.dev.houseonlinemarket.entities.domains.Role;
import tmrv.dev.houseonlinemarket.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);


}
