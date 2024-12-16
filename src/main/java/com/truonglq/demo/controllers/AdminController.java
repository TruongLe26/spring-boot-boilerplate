package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.requests.UserUpdatingRequest;
import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.StandardApiResponse;
import com.truonglq.demo.dtos.responses.UserUpdatingResponse;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.services.admin.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    AdminService adminService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    StandardApiResponse<String> welcome() {
        return StandardApiResponse.success("Welcome to admin page!");
    }

    @GetMapping("/users/{id}/{limit}")
    @ResponseStatus(HttpStatus.OK)
    StandardApiResponse<List<User>> fetchUsers(@PathVariable long id, @PathVariable int limit) {
        return StandardApiResponse.success(adminService.fetchNextPage(id, limit), "Users fetched.");
    }

    @PutMapping("/users/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    StandardApiResponse<UserUpdatingResponse> updateUserInformation(@PathVariable("id") String id, @RequestBody UserUpdatingRequest request) {
        return StandardApiResponse.success(adminService.updateUser(id, request), "User information updated.");
    }

}
