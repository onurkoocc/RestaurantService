package com.qrmenu.RestaurantService.config.security;

import com.qrmenu.RestaurantService.config.security.dto.UserIdentity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserIdentityProvider {

    public static UserIdentity getCurrentUserIdentity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserIdentity) {
            return (UserIdentity) authentication.getPrincipal();
        }
        return null; // Or throw an exception if the user is not authenticated
    }
}
