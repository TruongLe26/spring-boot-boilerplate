package com.truonglq.demo.services.authentication;

import com.nimbusds.jose.JOSEException;
import com.truonglq.demo.controllers.LoginController;
import com.truonglq.demo.dtos.requests.IntrospectRequest;
import com.truonglq.demo.dtos.requests.LoginRequest;
import com.truonglq.demo.dtos.responses.AuthenticationResponse;
import com.truonglq.demo.dtos.responses.IntrospectResponse;
import com.truonglq.demo.dtos.requests.AuthenticationRequest;
import com.truonglq.demo.dtos.responses.LoginResponse;
import com.truonglq.demo.dtos.responses.UserResponse;

import java.text.ParseException;

public interface AuthenticationService {
    LoginResponse authenticateAndGetToken(LoginRequest request);
}
