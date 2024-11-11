package com.qrmenu.RestaurantService.config.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserIdentity implements Serializable {
    private UUID userId;
    private String username;
    private String role;
}
