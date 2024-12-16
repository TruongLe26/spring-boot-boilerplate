package com.truonglq.demo.services.admin.impl;

import com.truonglq.demo.dtos.requests.UserUpdatingRequest;
import com.truonglq.demo.dtos.responses.UserUpdatingResponse;
import com.truonglq.demo.exceptions.AppException;
import com.truonglq.demo.exceptions.ErrorCode;
import com.truonglq.demo.mappers.UserMapper;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.repositories.UserRepository;
import com.truonglq.demo.services.admin.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminServiceImpl implements AdminService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> fetchNextPage(long id, int limit) {
        return userRepository.fetchAll(id, limit);
    }

    @Override
    @Transactional
    public UserUpdatingResponse updateUser(String id, UserUpdatingRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserUpdatingResponse(user);
    }
}
