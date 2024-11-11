package com.qrmenu.RestaurantService.domain.model.dto;

import com.qrmenu.RestaurantService.domain.mapper.UUIDAttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    private Integer tableCount;
    private String accountInfo;
    private Set<UUID> managerIds;
    private Set<UUID> waiterIds;
    private Set<UUID> cookIds;
}
