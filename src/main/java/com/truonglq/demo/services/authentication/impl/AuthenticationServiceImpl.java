package com.truonglq.demo.services.authentication.impl;

import com.truonglq.demo.dtos.requests.LoginRequest;
import com.truonglq.demo.dtos.responses.LoginResponse;
import com.truonglq.demo.exceptions.AppException;
import com.truonglq.demo.exceptions.ErrorCode;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.repositories.UserRepository;
import com.truonglq.demo.services.authentication.AuthenticationService;
import com.truonglq.demo.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    JwtService jwtService;

    @Override
    public LoginResponse authenticateAndGetToken(LoginRequest request) {
        User authenticatedUser = authenticate(request);
        return buildLoginResponse(authenticatedUser);
    }

    // Not using role for now
    private User authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
    }

    private LoginResponse buildLoginResponse(User user) {
        return LoginResponse.builder()
                .token(jwtService.generateToken(user))
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }

}
