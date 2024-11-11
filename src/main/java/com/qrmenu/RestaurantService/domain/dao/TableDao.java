package com.qrmenu.RestaurantService.domain.dao;

import com.qrmenu.RestaurantService.domain.model.dto.TableDto;
import com.qrmenu.RestaurantService.domain.model.enums.TableStatus;

import java.util.List;

public interface TableDao {
    List<TableDto> createAll(Long restaurantId, Long tableCount);
    TableDto getTable(Long tableId);
    Boolean updateTableStatus(Long tableId, TableStatus newStatus);
    List<TableDto> getAllTablesByRestaurantId(Long restaurantId);
}
