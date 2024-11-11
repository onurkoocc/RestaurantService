package com.qrmenu.RestaurantService.domain.dao.impl;


import com.qrmenu.RestaurantService.domain.dao.RestaurantDao;
import com.qrmenu.RestaurantService.domain.mapper.RestaurantMapper;
import com.qrmenu.RestaurantService.domain.model.dto.RestaurantDto;
import com.qrmenu.RestaurantService.domain.model.entity.Restaurant;
import com.qrmenu.RestaurantService.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDaoImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDto save(RestaurantDto restaurantDto) {
        Restaurant restaurant = RestaurantMapper.convertToEntity(restaurantDto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return RestaurantMapper.convertToDto(savedRestaurant);
    }

    @Override
    public Optional<RestaurantDto> findById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).map(RestaurantMapper::convertToDto);
    }

    @Override
    public RestaurantDto update(RestaurantDto restaurantDto) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantDto.getId());
        if (!restaurantOptional.isPresent()) {
            throw new RuntimeException("Restaurant not found");
        }

        Restaurant restaurant = restaurantOptional.get();
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setTableCount(restaurantDto.getTableCount());
        restaurant.setAccountInfo(restaurantDto.getAccountInfo());
        restaurant.setManagerIds(restaurantDto.getManagerIds());
        restaurant.setWaiterIds(restaurantDto.getWaiterIds());
        restaurant.setCookIds(restaurantDto.getCookIds());
        return RestaurantMapper.convertToDto(restaurantRepository.save(restaurant));
    }


}
