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
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationServiceImpl implements RegistrationService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    @Override
    public UserResponse registerNewLockedUser(UserRegistrationRequest request) {
        Optional<User> existingUserWithUsername = userRepository.findByUsername(request.getUsername());

        if (existingUserWithUsername.isPresent()) {
//            throw new AppException(ErrorCode.USER_EXISTED);
            throw new UserAlreadyExistsException();
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
//                .status(false)
                .build();

        // Send OTP

        Role userRole = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.addRole(userRole);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

}
