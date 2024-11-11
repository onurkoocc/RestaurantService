package com.qrmenu.RestaurantService.domain.model.dto;

import lombok.Data;

@Data
public class TableQrCodeDto {
    private TableDto tableDto;
    private String qrCodeBase64;
}
