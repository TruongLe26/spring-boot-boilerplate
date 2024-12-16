package com.truonglq.demo.services.admin;

import com.truonglq.demo.dtos.requests.UserUpdatingRequest;
import com.truonglq.demo.dtos.responses.UserUpdatingResponse;
import com.truonglq.demo.models.entities.User;

import java.util.List;

public interface AdminService {
    List<User> fetchNextPage(long id, int limit);
    UserUpdatingResponse updateUser(String id, UserUpdatingRequest request);
}
