package com.truonglq.demo.mappers;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRegistrationRequest registrationRequest);
    UserResponse toUserResponse(User user);
}
