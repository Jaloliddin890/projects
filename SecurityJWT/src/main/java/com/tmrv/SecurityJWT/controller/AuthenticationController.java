package com.tmrv.SecurityJWT.controller;


import com.tmrv.SecurityJWT.model.UserDto;


import com.tmrv.SecurityJWT.model.UserDtoForLogin;
import com.tmrv.SecurityJWT.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth Controller", description = "This Controller's api is based on Security")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserDtoForLogin dto){
        return ResponseEntity.ok(authenticationService.login(dto));
    }

}
