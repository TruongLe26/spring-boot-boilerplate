package com.truonglq.demo.services.admin;

import com.truonglq.demo.dtos.requests.UserUpdatingRequest;
import com.truonglq.demo.dtos.responses.UserUpdatingResponse;

public interface AdminService {
    UserUpdatingResponse updateUser(String id, UserUpdatingRequest request);
}
