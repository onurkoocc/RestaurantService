package com.qrmenu.RestaurantService.domain.repository;

import com.qrmenu.RestaurantService.domain.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
