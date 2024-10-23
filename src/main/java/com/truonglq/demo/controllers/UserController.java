package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.services.user.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demo/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRegistrationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        System.out.println("data");
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<User>> getUsers() {
        return ApiResponse.<List<User>>builder()
                .result(userService.getUsers())
                .build();
//        return userService.getUsers();
    }

    @GetMapping("/search")
    ApiResponse<Page<User>> getUsersByUsername(@RequestParam String username,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        ApiResponse<Page<User>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUsersByUsername(username, page, size));
        return apiResponse;
    }

    @GetMapping("/me")
    ApiResponse<UserResponse> getCurrentUser() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getCurrentUser())
                .build();
    }

}
