package tmrv.dev.springprofilingfreemakerlogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmrv.dev.springprofilingfreemakerlogging.model.AuthUser;
import tmrv.dev.springprofilingfreemakerlogging.model.AuthUser;

public interface UserRepository extends JpaRepository<AuthUser, Long> {
}