package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.services.registration.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationController {

    RegistrationService registrationService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ApiResponse.<UserResponse>builder().result(registrationService.registerNewLockedUser(userRegistrationRequest)).build();
    }

}
