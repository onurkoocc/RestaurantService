package com.qrmenu.RestaurantService.domain.model.dto;

import com.qrmenu.RestaurantService.domain.model.enums.TableStatus;
import lombok.Data;

@Data
public class TableDto {
    private Long id;
    private Long restaurantId;
    private Long tableOrder;
    private TableStatus status; // AVAILABLE, OCCUPIED
}
