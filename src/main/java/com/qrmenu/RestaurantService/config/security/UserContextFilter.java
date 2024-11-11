package com.qrmenu.RestaurantService.config.security;

import com.qrmenu.RestaurantService.config.security.dto.UserIdentity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class UserContextFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public UserContextFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String token = authHeader.substring(7);

                if (jwtTokenProvider.validateToken(token)) {

                    // Extract UserIdentity from token
                    UserIdentity userIdentity = jwtTokenProvider.getUserIdentityFromToken(token);

                    // Prefix the role with "ROLE_" if not already prefixed
                    String roleWithPrefix = userIdentity.getRole().startsWith("ROLE_") ?
                            userIdentity.getRole() : "ROLE_" + userIdentity.getRole();

                    // Create authorities list
                    List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority(roleWithPrefix)
                    );

                    // Create Authentication object with UserIdentity as principal
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userIdentity, token, authorities);

                    // Set the Authentication in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            // Log the exception and proceed without setting authentication
            logger.error("Could not set user authentication in security context", e);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
