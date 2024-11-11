package com.qrmenu.RestaurantService.domain.mapper;

import com.qrmenu.RestaurantService.domain.model.dto.RestaurantDto;
import com.qrmenu.RestaurantService.domain.model.entity.Restaurant;

public class RestaurantMapper {
    public static Restaurant convertToEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setTableCount(restaurantDto.getTableCount());
        restaurant.setAccountInfo(restaurantDto.getAccountInfo());
        restaurant.setManagerIds(restaurantDto.getManagerIds());
        restaurant.setWaiterIds(restaurantDto.getWaiterIds());
        restaurant.setCookIds(restaurantDto.getCookIds());
        return restaurant;
    }

    public static RestaurantDto convertToDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setTableCount(restaurant.getTableCount());
        restaurantDto.setAccountInfo(restaurant.getAccountInfo());
        restaurantDto.setManagerIds(restaurant.getManagerIds());
        restaurantDto.setWaiterIds(restaurant.getWaiterIds());
        restaurantDto.setCookIds(restaurant.getCookIds());
        return restaurantDto;
    }
}
