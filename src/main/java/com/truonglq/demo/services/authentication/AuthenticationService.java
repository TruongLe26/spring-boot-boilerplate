package com.truonglq.demo.services.authentication;

import com.nimbusds.jose.JOSEException;
import com.truonglq.demo.dtos.requests.IntrospectRequest;
import com.truonglq.demo.dtos.responses.AuthenticationResponse;
import com.truonglq.demo.dtos.responses.IntrospectResponse;
import com.truonglq.demo.dtos.requests.AuthenticationRequest;

import java.text.ParseException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
