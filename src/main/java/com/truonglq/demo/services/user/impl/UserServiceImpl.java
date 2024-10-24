package com.truonglq.demo.services.user.impl;

import com.truonglq.demo.dtos.requests.UserRegistrationRequest;
import com.truonglq.demo.dtos.responses.UserResponse;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.exceptions.AppException;
import com.truonglq.demo.exceptions.ErrorCode;
import com.truonglq.demo.mappers.UserMapper;
import com.truonglq.demo.repositories.UserRepository;
import com.truonglq.demo.services.authentication.AuthenticationService;
import com.truonglq.demo.services.user.UserService;
import com.truonglq.demo.services.user.UserSpecifications;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
//    AuthenticationService authenticationService;

    @Override
    public UserResponse createUser(UserRegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setStatus(true);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll(); // print all
    }

    @Override
    public Page<User> getUsersByUsername(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(UserSpecifications.usernameContains(username), pageable);
    }

    @Override
    public UserResponse getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                List.of()
//        );
        return (UserDetails) user;
    }
}
