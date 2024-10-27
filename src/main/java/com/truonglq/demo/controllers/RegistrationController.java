package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.StandardApiResponse;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.services.registration.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationController {

    RegistrationService registrationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    StandardApiResponse<UserResponse> register(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        return StandardApiResponse.success(registrationService.registerNewLockedUser(userRegistrationRequest), "User registered successfully.");
    }

}
