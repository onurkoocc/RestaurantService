package com.qrmenu.RestaurantService.domain.service;


import com.qrmenu.RestaurantService.domain.model.dto.RestaurantDto;

public interface RestaurantService {
    RestaurantDto createRestaurant(RestaurantDto restaurantDto);
    RestaurantDto updateRestaurant(RestaurantDto restaurantDto);
    RestaurantDto getRestaurantDetails(Long restaurantId);
}
