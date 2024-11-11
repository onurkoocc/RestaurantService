package com.qrmenu.RestaurantService.domain.mapper;

import com.qrmenu.RestaurantService.domain.model.dto.TableDto;
import com.qrmenu.RestaurantService.domain.model.entity.Restaurant;
import com.qrmenu.RestaurantService.domain.model.entity.RestaurantTable;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTableMapper {

    // Convert a single entity to DTO
    public static TableDto toDto(RestaurantTable entity) {
        if (entity == null) {
            return null;
        }

        TableDto dto = new TableDto();
        dto.setId(entity.getId());
        dto.setTableOrder(entity.getTableOrder() != null ? entity.getTableOrder().longValue() : null);
        dto.setStatus(entity.getStatus());
        dto.setRestaurantId(entity.getRestaurant() != null ? entity.getRestaurant().getId() : null);

        return dto;
    }

    // Convert a list of entities to a list of DTOs
    public static List<TableDto> toDtoList(List<RestaurantTable> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(RestaurantTableMapper::toDto)
                .collect(Collectors.toList());
    }

    // Convert a single DTO to entity without setting the Restaurant
    public static RestaurantTable toEntity(TableDto dto) {
        if (dto == null) {
            return null;
        }

        RestaurantTable entity = new RestaurantTable();
        entity.setId(dto.getId());
        entity.setTableOrder(dto.getTableOrder() != null ? dto.getTableOrder().intValue() : null);
        entity.setStatus(dto.getStatus());
        // Restaurant needs to be set separately
        return entity;
    }

    // Convert a single DTO to entity with Restaurant
    public static RestaurantTable toEntity(TableDto dto, Restaurant restaurant) {
        RestaurantTable entity = toEntity(dto);
        entity.setRestaurant(restaurant);
        return entity;
    }

    // Convert a list of DTOs to a list of entities without setting the Restaurant
    public static List<RestaurantTable> toEntityList(List<TableDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(RestaurantTableMapper::toEntity)
                .collect(Collectors.toList());
    }

    // Convert a list of DTOs to a list of entities with the same Restaurant
    public static List<RestaurantTable> toEntityList(List<TableDto> dtos, Restaurant restaurant) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(dto -> toEntity(dto, restaurant))
                .collect(Collectors.toList());
    }
}
