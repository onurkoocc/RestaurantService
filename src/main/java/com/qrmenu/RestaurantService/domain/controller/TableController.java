package com.qrmenu.RestaurantService.domain.controller;

import com.qrmenu.RestaurantService.config.security.UserIdentityProvider;
import com.qrmenu.RestaurantService.config.security.dto.UserIdentity;
import com.qrmenu.RestaurantService.domain.model.dto.TableDto;
import com.qrmenu.RestaurantService.domain.model.dto.TableQrCodeDto;
import com.qrmenu.RestaurantService.domain.model.enums.TableStatus;
import com.qrmenu.RestaurantService.domain.service.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService){
        this.tableService = tableService;
    }

    @PostMapping("/tables/createAll")
    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    public ResponseEntity<List<TableDto>> createAll(@RequestParam Long restaurantId, @RequestParam Long tableCount){
        List<TableDto> tableDtos = tableService.createAll(restaurantId, tableCount);
        if(tableDtos != null){
            return ResponseEntity.ok(tableDtos);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<TableDto> getTable(@PathVariable Long tableId){
        TableDto tableDto = tableService.getTable(tableId);
        if(tableDto != null){
            return ResponseEntity.ok(tableDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{restaurantId}/tables")
    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'WAITER')")
    public ResponseEntity<List<TableDto>> getAllTablesByRestaurantId(@PathVariable Long restaurantId){
        List<TableDto> tableDtos = tableService.getAllTablesByRestaurantId(restaurantId);
        if(tableDtos != null){
            return ResponseEntity.ok(tableDtos);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/tables/{tableId}/status")
    @PreAuthorize("hasAnyRole('WAITER')")
    public ResponseEntity<Void> updateTableStatus(@PathVariable Long tableId, @RequestParam TableStatus newStatus){
        Boolean updated = tableService.updateTableStatus(tableId, newStatus);
        if(updated){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/generateQrCodes")
    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    public ResponseEntity<List<TableQrCodeDto>> generateQrCodes(
            @RequestParam Long restaurantId) {
        List<TableQrCodeDto> qrCodes = tableService.generateQrCodes(restaurantId);
        if (qrCodes != null && !qrCodes.isEmpty()) {
            return ResponseEntity.ok(qrCodes);
        }
        return ResponseEntity.badRequest().build();
    }
}
