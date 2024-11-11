package com.qrmenu.RestaurantService.domain.repository;

import com.qrmenu.RestaurantService.domain.model.entity.RestaurantTable;
import com.qrmenu.RestaurantService.domain.model.enums.TableStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE RestaurantTable rt SET rt.status = :status WHERE rt.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") TableStatus status);

    List<RestaurantTable> findByRestaurantId(Long restaurantId);
    Boolean existsByRestaurantId(Long restaurantId);
}
