package com.library.controllers;

import com.library.dto.user.UserLoginRequestDto;
import com.library.dto.user.UserLoginResponseDto;
import com.library.dto.user.UserRegistrationRequestDto;
import com.library.dto.user.UserResponseDto;
import com.library.exception.RegistrationException;
import com.library.security.AuthenticationService;
import com.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Tag(name = "Authentication management", description = "Endpoint for authenticate user in the application")
@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @Operation(summary = "Register a user", description = "Register a new user if it is not exist in the db")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request) throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Endpoint for logging in a user")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
