package uz.pdp.hateoas.hateoas;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/authUsers")
public class AuthUserController {


    private final AuthUserRepository authUserRepository;

    public AuthUserController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @PostMapping("/create")
    public EntityModel<AuthUser> create(@RequestBody AuthUserDto authUser) {
        AuthUser user = authUserRepository.getUserByUsername(authUser.getUsername()).orElse(null);
        if (user != null) {
            System.out.println("User already exists");
            return EntityModel.of(user);
        }
        user = new AuthUser();
        user.setUsername(authUser.getUsername());
        user.setPassword(authUser.getPassword());
        user = authUserRepository.save(user);
        Link link = Link.of("http://localhost:8080/api/authUsers/" + user.getUsername());
        return EntityModel.of(user, link);
    }


    @GetMapping("/getAll")
    public CollectionModel<EntityModel<AuthUser>> getAll() {
        List<AuthUser> users = authUserRepository.findAll();
        List<EntityModel<AuthUser>> models = users.stream().map(authUser -> {
            Link link = Link.of("http://localhost:8080/api/authUsers/" + authUser.getUsername());
            return EntityModel.of(authUser, link);
        }).toList();
        return CollectionModel.of(models, Link.of("http://localhost:8080/api/authUsers/all"));
    }

    @PutMapping("/update/{id}")
    public EntityModel<AuthUser> update(@RequestBody AuthUserDto authUserDto, @PathVariable Long id) {
        AuthUser user = authUserRepository.
                getUserById(id).orElseThrow
                        (() -> new RuntimeException("User not found"));
        user.setUsername(authUserDto.getUsername());
        user.setPassword(authUserDto.getPassword());
        user = authUserRepository.save(user);
        Link link = Link.of("http://localhost:8080/api/authUsers/" + user.getId());
        return EntityModel.of(user, link);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        AuthUser user = authUserRepository.
                getUserById(id).orElseThrow
                        (() -> new RuntimeException("User not found"));
        authUserRepository.delete(user);
    }
}
