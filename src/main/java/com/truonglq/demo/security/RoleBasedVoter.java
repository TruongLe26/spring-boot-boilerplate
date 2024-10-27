package com.truonglq.demo.security;

import com.truonglq.demo.models.entities.Role;
import com.truonglq.demo.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleBasedVoter implements AuthorizationManager<RequestAuthorizationContext> {

    RoleRepository roleRepository;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        if (authentication.get().getPrincipal() instanceof UserDetails userDetails) {
            String requestURI = object.getRequest().getRequestURI();

            List<Role> roles = roleRepository.findByUsers_Username(userDetails.getUsername());

            for (Role role : roles) {
                if (role.getRestrictedEndpoints().contains(requestURI)) {
                    return new AuthorizationDecision(false);
                }
            }
        }

        return new AuthorizationDecision(true);
    }
}
