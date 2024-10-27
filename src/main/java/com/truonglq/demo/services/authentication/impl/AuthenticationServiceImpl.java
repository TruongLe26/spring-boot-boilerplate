package com.truonglq.demo.services.authentication.impl;

import com.truonglq.demo.dtos.requests.LoginRequest;
import com.truonglq.demo.dtos.responses.LoginResponse;
import com.truonglq.demo.exceptions.AppException;
import com.truonglq.demo.exceptions.ErrorCode;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.repositories.UserRepository;
import com.truonglq.demo.services.authentication.AuthenticationService;
import com.truonglq.demo.services.jwt.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    AuthenticationManager authenticationManager;
    JwtService jwtService;

    @Bean
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public LoginResponse authenticateAndGetToken(LoginRequest request) {
        UserDetails authenticatedUser = authenticate(request);
        return buildLoginResponse(authenticatedUser);
    }

    private UserDetails authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        return (UserDetails) authentication.getPrincipal();
//        return userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
    }

    private LoginResponse buildLoginResponse(UserDetails userDetails) {
        String jwtToken = jwtService.generateToken(userDetails);
        String jwtRefreshToken = jwtService.generateRefreshToken(userDetails);

        return LoginResponse.builder()
                .token(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

}
