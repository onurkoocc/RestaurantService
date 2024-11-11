package com.qrmenu.RestaurantService.domain.service.impl;


import com.qrmenu.RestaurantService.domain.dao.RestaurantDao;
import com.qrmenu.RestaurantService.domain.model.dto.RestaurantDto;
import com.qrmenu.RestaurantService.domain.service.RestaurantService;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantDao restaurantDao;

    public RestaurantServiceImpl(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        return restaurantDao.save(restaurantDto);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto) {
        if(restaurantDto.getId() == null){
            return null;
        }
        return restaurantDao.update(restaurantDto);
    }

    @Override
    public RestaurantDto getRestaurantDetails(Long restaurantId) {
        return restaurantDao.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }
}
