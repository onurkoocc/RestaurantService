package com.qrmenu.RestaurantService.domain.dao;


import com.qrmenu.RestaurantService.domain.model.dto.RestaurantDto;

import java.util.Optional;

public interface RestaurantDao {
    RestaurantDto save(RestaurantDto restaurantDto);

    Optional<RestaurantDto> findById(Long restaurantId);

    RestaurantDto update(RestaurantDto restaurantDto);
}
