package uz.pdp.springbootdatajpa.JpaRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @GetMapping
    public Page<Post> getAll(
            @RequestParam(value = "number", defaultValue = "0") int number,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(number, size);
        return postRepository.findAll(pageable);
    }

    @GetMapping("{userId}")
    public List<Post> getAllByUserID( @PathVariable Integer userId) {
        return postRepository.findByUserId(userId);
    }
    @GetMapping("/users/{userIds}")
    public List<Post> getUsersById(@PathVariable Collection<Integer> userIds) {
        return postRepository.findUsersByID(userIds);
    }
    @GetMapping("/sort")
    public List<Post> getAllBySortedID() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return postRepository.getAllBySortedID(sort);
    }

    @GetMapping("/delete/{userId}")
    public void deletePostByID( @PathVariable Integer userId) {
       postRepository.deletePostByID(userId);

    }

    @GetMapping("/query/{title}")
    public List<Post> getPostByTitle(@PathVariable String title) {
        return postRepository.findByTitle(title);
    }
}
