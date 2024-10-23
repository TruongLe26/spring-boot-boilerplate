package com.truonglq.demo.services.registration;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.UserResponse;

public interface RegistrationService {
    UserResponse registerNewLockedUser(UserRegistrationRequest request);
}
