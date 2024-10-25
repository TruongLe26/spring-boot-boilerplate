package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.requests.UserUpdatingRequest;
import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.UserUpdatingResponse;
import com.truonglq.demo.services.admin.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    AdminService adminService;

    @GetMapping
    ApiResponse<String> welcome() {
        return ApiResponse.<String>builder()
                .result("Welcome to admin page!")
                .build();
    }

    @PutMapping("/users/update/{id}")
    ApiResponse<UserUpdatingResponse> updateUserInformation(@PathVariable("id") String id, @RequestBody UserUpdatingRequest request) {
        return ApiResponse.<UserUpdatingResponse>builder()
                .result(adminService.updateUser(id, request))
                .build();
    }

}
