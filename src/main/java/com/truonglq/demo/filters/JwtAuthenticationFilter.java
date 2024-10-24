package com.truonglq.demo.filters;

import com.truonglq.demo.services.JwtAuthenticationEntryPoint;
import com.truonglq.demo.services.jwt.JwtService;
import com.truonglq.demo.services.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
        HandlerExceptionResolver handlerExceptionResolver;
        JwtService jwtService;
//        UserDetailsService userDetailsService;
        UserService userService;
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//        BlacklistTokenService blacklistTokenService;

        @Override
        protected void doFilterInternal(
                @NonNull HttpServletRequest request,
                @NonNull HttpServletResponse response,
                @NonNull FilterChain filterChain) throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            try {
                final String jwt = authHeader.substring(7);
//                if (blacklistTokenService.isBlacklisted(jwt)) {
//                    filterChain.doFilter(request, response);
//                    return;
//                }
                final String username = jwtService.extractUsername(jwt);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (username != null && authentication == null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request, response);
            } catch (Exception exception) {
//                handlerExceptionResolver.resolveException(request, response, null, exception);
                jwtAuthenticationEntryPoint.commence(request, response, null);
            }
        }
}
