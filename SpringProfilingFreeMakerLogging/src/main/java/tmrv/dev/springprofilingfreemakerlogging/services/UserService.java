package tmrv.dev.springprofilingfreemakerlogging.services;


import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;
import tmrv.dev.springprofilingfreemakerlogging.model.AuthUser;

import tmrv.dev.springprofilingfreemakerlogging.model.UserDto;
import tmrv.dev.springprofilingfreemakerlogging.repository.UserRepository;

@Service
public class UserService {

private final UserRepository userRepository;
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
    public AuthUser register(UserDto userDto) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(userDto.username());
        authUser.setPassword(userDto.password());
        authUser.setPhoneNumber(userDto.phoneNumber());

        return userRepository.save(authUser);
    }
}
