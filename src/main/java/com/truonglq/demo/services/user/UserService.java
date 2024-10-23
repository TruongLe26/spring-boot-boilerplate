package com.truonglq.demo.services.user;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.models.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRegistrationRequest request);
    List<User> getUsers();
    Page<User> getUsersByUsername(String username, int page, int size);
}
