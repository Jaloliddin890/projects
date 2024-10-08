package tmrv.dev.springprofilingfreemakerlogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmrv.dev.springprofilingfreemakerlogging.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}