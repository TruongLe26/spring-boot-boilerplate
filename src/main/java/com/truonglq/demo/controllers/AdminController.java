package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    ApiResponse<String> welcome() {
        return ApiResponse.<String>builder()
                .result("Welcome to admin page!")
                .build();
    }

}
