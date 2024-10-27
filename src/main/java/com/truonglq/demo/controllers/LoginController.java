package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.requests.LoginRequest;
import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.LoginResponse;
import com.truonglq.demo.dtos.responses.StandardApiResponse;
import com.truonglq.demo.services.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    StandardApiResponse<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {
        return StandardApiResponse.success(authenticationService.authenticateAndGetToken(request), "Login successful.");
    }

}
