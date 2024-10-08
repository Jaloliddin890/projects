package tmrv.dev.springprofilingfreemakerlogging.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tmrv.dev.springprofilingfreemakerlogging.getYamlValues.DevConfig;
import tmrv.dev.springprofilingfreemakerlogging.getYamlValues.DevProperties;
import tmrv.dev.springprofilingfreemakerlogging.getYamlValues.TestConfig;
import tmrv.dev.springprofilingfreemakerlogging.getYamlValues.TestProperties;
import tmrv.dev.springprofilingfreemakerlogging.model.AuthUser;
import tmrv.dev.springprofilingfreemakerlogging.model.Post;
import tmrv.dev.springprofilingfreemakerlogging.model.AuthUser;
import tmrv.dev.springprofilingfreemakerlogging.model.UserDto;
import tmrv.dev.springprofilingfreemakerlogging.repository.PostRepository;
import tmrv.dev.springprofilingfreemakerlogging.repository.UserRepository;
import tmrv.dev.springprofilingfreemakerlogging.services.JavaMailSenderService;
import tmrv.dev.springprofilingfreemakerlogging.services.UserService;

import java.util.List;

@RestController("/api")
public class Controller {


    private final PostRepository postRepository;
    private final TestProperties testProperties;
    private final DevProperties devProperties;
    private final UserService userService;
    private final JavaMailSenderService mailSenderService;


    public Controller(
            PostRepository postRepository, TestProperties testProperties, DevProperties devProperties, UserService userService, JavaMailSenderService mailSenderService) {
        this.postRepository = postRepository;
        this.testProperties = testProperties;
        this.devProperties = devProperties;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
    }


    @GetMapping("/getAll")
    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/getYamlValuesFromTest")
    public TestConfig getYamlValuesFromTest() {
        return testProperties.get();
    }

    @GetMapping("/getYamlValuesFromDev")
    public DevConfig getYamlValuesFromDev() {
        return devProperties.get();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody UserDto dto) {
        mailSenderService.sendFreemarkerMail(dto.username());
        return ResponseEntity.ok(userService.register(dto));
    }

}
