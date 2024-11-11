package com.qrmenu.RestaurantService.domain.controller;


import com.qrmenu.RestaurantService.domain.model.dto.RestaurantDto;
import com.qrmenu.RestaurantService.domain.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        RestaurantDto createdRestaurant = restaurantService.createRestaurant(restaurantDto);
        return ResponseEntity.ok(createdRestaurant);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER')")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurantDto) {
        RestaurantDto updatedRestaurant = restaurantService.updateRestaurant(restaurantDto);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @GetMapping("/{restaurantId}")
    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER')")
    public ResponseEntity<RestaurantDto> getRestaurantDetails(@PathVariable Long restaurantId) {
        RestaurantDto restaurant = restaurantService.getRestaurantDetails(restaurantId);
        return ResponseEntity.ok(restaurant);
    }
}
