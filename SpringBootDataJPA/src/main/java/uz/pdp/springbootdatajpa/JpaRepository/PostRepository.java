package uz.pdp.springbootdatajpa.JpaRepository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(name = "Post.getAllByUserID")
    List<Post> findByUserId(Integer userId);

    @Query(value = "select p from Post p")
    List<Post> getAllBySortedID(Sort sort);

    @Query(value = "from Post p where p.userId in (?1)")
    List<Post> findUsersByID(Collection<Integer> userIds);

    @Modifying
    @Query(value = "delete Post p where p.userId=?1")
    void deletePostByID(Integer userId);

    List<Post> findByTitle(String title);
}
