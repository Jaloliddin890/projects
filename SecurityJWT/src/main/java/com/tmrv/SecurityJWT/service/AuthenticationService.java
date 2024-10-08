package com.tmrv.SecurityJWT.service;


import com.tmrv.SecurityJWT.model.UserDto;

import com.tmrv.SecurityJWT.model.User;
import com.tmrv.SecurityJWT.model.UserDtoForLogin;
import com.tmrv.SecurityJWT.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public String register(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.firstname());
        user.setLastname(userDto.lastname());
        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRole(userDto.role());

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
