package com.truonglq.demo.services.user;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserResponse createUser(UserRegistrationRequest request);
    List<User> getUsers();
    Page<User> getUsersByUsername(String username, int page, int size);
    UserResponse getCurrentUser();
}
