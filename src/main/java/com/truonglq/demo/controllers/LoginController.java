package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.requests.LoginRequest;
import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.LoginResponse;
import com.truonglq.demo.services.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.<LoginResponse>builder()
                .result(authenticationService.authenticateAndGetToken(request))
                .build();
    }

}
