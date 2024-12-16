package com.truonglq.demo.services.registration.impl;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.exceptions.AppException;
import com.truonglq.demo.exceptions.ErrorCode;
import com.truonglq.demo.exceptions.UserAlreadyExistsException;
import com.truonglq.demo.mappers.UserMapper;
import com.truonglq.demo.models.entities.Role;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.models.enums.RoleEnum;
import com.truonglq.demo.repositories.RoleRepository;
import com.truonglq.demo.repositories.UserRepository;
import com.truonglq.demo.services.registration.RegistrationService;
import com.truonglq.demo.services.role.RoleService;
import com.truonglq.demo.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationServiceImpl implements RegistrationService {

    UserService userService;
    RoleService roleService;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    @Override
    public UserResponse registerNewLockedUser(UserRegistrationRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
//            throw new AppException(ErrorCode.USER_EXISTED);
            throw new UserAlreadyExistsException();
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
//                .status(false)
                .build();

        // Send OTP

        Role userRole = roleService.findByName(RoleEnum.USER);
        user.addRole(userRole);
        userService.saveNewUser(user);

        return userMapper.toUserResponse(user);
    }

}
