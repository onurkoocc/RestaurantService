package com.qrmenu.RestaurantService.domain.service;

import com.qrmenu.RestaurantService.domain.model.dto.TableDto;
import com.qrmenu.RestaurantService.domain.model.dto.TableQrCodeDto;
import com.qrmenu.RestaurantService.domain.model.enums.TableStatus;

import java.util.List;

public interface TableService {
    List<TableDto> createAll(Long restaurantId, Long tableCount);
    TableDto getTable(Long tableId);
    List<TableDto> getAllTablesByRestaurantId(Long restaurantId);
    Boolean updateTableStatus(Long tableId, TableStatus newStatus);
    List<TableQrCodeDto> generateQrCodes(Long restaurantId);
}
