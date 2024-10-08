package tmrv.dev.houseonlinemarket.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tmrv.dev.houseonlinemarket.dto.UserDto;
import tmrv.dev.houseonlinemarket.dto.UserDtoForLogin;
import tmrv.dev.houseonlinemarket.entities.User;
import tmrv.dev.houseonlinemarket.repository.UserRepository;
import tmrv.dev.houseonlinemarket.service.security.JwtService;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public String register(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRole(userDto.role());
        user.setActive(true);

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(UserDtoForLogin dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()
                )
        );
        User user = userRepository.findByUsername(dto.username()).orElseThrow();

        return jwtService.generateToken(user);
    }
}

