package com.truonglq.demo.services.authentication;

import com.truonglq.demo.dtos.requests.LoginRequest;
import com.truonglq.demo.dtos.responses.LoginResponse;
import com.truonglq.demo.services.authentication.impl.AuthenticationServiceImpl;
import com.truonglq.demo.services.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private LoginRequest loginRequest;
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        loginRequest = new LoginRequest("test","tests");
        userDetails = mock(UserDetails.class);
    }

    @Test
    public void authenticateAndGetToken_ShouldReturnLoginResponse_WhenAuthenticationIsSuccessful() {
        String expectedToken = "accessToken";
        String expectedRefreshToken = "refreshToken";

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        when(jwtService.generateToken(userDetails)).thenReturn(expectedToken);
        when(jwtService.generateRefreshToken(userDetails)).thenReturn(expectedRefreshToken);

        LoginResponse response = authenticationService.authenticateAndGetToken(loginRequest);

        assertEquals(expectedToken, response.getToken());
        assertEquals(expectedRefreshToken, response.getRefreshToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken(userDetails);
        verify(jwtService).generateRefreshToken(userDetails);
    }

    @Test
    public void authenticateAndGetToken_ShouldThrowException_WhenAuthenticationFails() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

        try {
            authenticationService.authenticateAndGetToken(loginRequest);
        } catch (RuntimeException e) {
            assertEquals("Authentication failed", e.getMessage());
        }

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, never()).generateToken(any());
        verify(jwtService, never()).generateRefreshToken(any());
    }

}
