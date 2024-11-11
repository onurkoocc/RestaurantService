package com.qrmenu.RestaurantService.domain.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qrmenu.RestaurantService.domain.dao.TableDao;
import com.qrmenu.RestaurantService.domain.model.dto.TableDto;
import com.qrmenu.RestaurantService.domain.model.dto.TableQrCodeDto;
import com.qrmenu.RestaurantService.domain.model.enums.TableStatus;
import com.qrmenu.RestaurantService.domain.service.TableService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    private static final String BASE_URL = "localhost:8080";
    private final TableDao tableDao;

    public TableServiceImpl(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public List<TableDto> createAll(Long restaurantId, Long tableCount) {
        return tableDao.createAll(restaurantId, tableCount);
    }

    @Override
    public TableDto getTable(Long tableId) {
        return tableDao.getTable(tableId);
    }

    @Override
    public List<TableDto> getAllTablesByRestaurantId(Long restaurantId) {
        return tableDao.getAllTablesByRestaurantId(restaurantId);
    }

    @Override
    public Boolean updateTableStatus(Long tableId, TableStatus newStatus) {
        return tableDao.updateTableStatus(tableId, newStatus);
    }


    @Override
    public List<TableQrCodeDto> generateQrCodes(Long restaurantId) {
        List<TableDto> tableDtos = tableDao.getAllTablesByRestaurantId(restaurantId);
        List<TableQrCodeDto> qrCodeDtos = new ArrayList<>();
        if (tableDtos != null) {
            for (TableDto tableDto : tableDtos) {
                String qrContent = String.format("%s/%d/%d", BASE_URL, restaurantId, tableDto.getId());
                String qrCodeBase64 = generateQrCodeBase64(qrContent);
                TableQrCodeDto qrCodeDto = new TableQrCodeDto();
                qrCodeDto.setTableDto(tableDto);
                qrCodeDto.setQrCodeBase64(qrCodeBase64);
                qrCodeDtos.add(qrCodeDto);
            }
        }
        return qrCodeDtos;
    }

    private String generateQrCodeBase64(String content) {
        try {
            int width = 300;
            int height = 300;
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(pngData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
