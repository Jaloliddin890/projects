package tmrv.dev.houseonlinemarket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tmrv.dev.houseonlinemarket.dto.UserDto;
import tmrv.dev.houseonlinemarket.dto.UserDtoForLogin;

@RestController
@RequestMapping("/authentication")
@Tag(name = "Authentication Controller", description = "Handles user registration and login")
public class AuthenticationController {

    private final tmrv.dev.houseonlinemarket.service.AuthenticationService authenticationService;

    public AuthenticationController(tmrv.dev.houseonlinemarket.service.AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @Operation(summary = "Register a new user", description = "Register a new User by providing their data")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid input or user already exists.")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDto userDto) {
        String register = authenticationService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);

    }
    @Operation(summary = "Login a user", description = "Allows a user to login by providing their credentials.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful."),
            @ApiResponse(responseCode = "401", description = "Invalid credentials.")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDtoForLogin dtoForLogin){
        String tokenForLogin = authenticationService.login(dtoForLogin);
        return ResponseEntity.status(HttpStatus.OK).body(tokenForLogin);
    }
}
