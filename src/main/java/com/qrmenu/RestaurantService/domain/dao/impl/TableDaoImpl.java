package com.qrmenu.RestaurantService.domain.dao.impl;

import com.qrmenu.RestaurantService.domain.dao.TableDao;
import com.qrmenu.RestaurantService.domain.mapper.RestaurantTableMapper;
import com.qrmenu.RestaurantService.domain.model.dto.TableDto;
import com.qrmenu.RestaurantService.domain.model.entity.Restaurant;
import com.qrmenu.RestaurantService.domain.model.entity.RestaurantTable;
import com.qrmenu.RestaurantService.domain.model.enums.TableStatus;
import com.qrmenu.RestaurantService.domain.repository.RestaurantRepository;
import com.qrmenu.RestaurantService.domain.repository.TableRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TableDaoImpl implements TableDao {
    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    public TableDaoImpl(TableRepository tableRepository,
                        RestaurantRepository restaurantRepository) {
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<TableDto> createAll(Long restaurantId, Long tableCount){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()){
            List<RestaurantTable> restaurantTables = buildRestaurantTables(restaurant.get(), tableCount);
            return RestaurantTableMapper.toDtoList(tableRepository.saveAll(restaurantTables));
        }
        return null;
    }

    @Override
    public TableDto getTable(Long tableId){
        if(tableId != null && tableRepository.existsById(tableId)){
            return RestaurantTableMapper.toDto(tableRepository.findById(tableId).get());
        }
        return null;
    }

    @Override
    public List<TableDto> getAllTablesByRestaurantId(Long restaurantId){
        if (restaurantRepository.existsById(restaurantId)){
            return RestaurantTableMapper.toDtoList(tableRepository.findByRestaurantId(restaurantId));
        }
        return null;
    }

    @Override
    public Boolean updateTableStatus(Long tableId, TableStatus newStatus){
        if(tableId != null && tableRepository.existsById(tableId)){
            tableRepository.updateStatusById(tableId, newStatus);
            return true;
        }
        return false;
    }

    private List<RestaurantTable> buildRestaurantTables(Restaurant restaurant, Long tableCount){
        List<RestaurantTable> restaurantTables = new ArrayList<>();
        for(int i=0; i<tableCount; i++){
            RestaurantTable restaurantTable = new RestaurantTable();
            restaurantTable.setRestaurant(restaurant);
            restaurantTable.setTableOrder(i);
            restaurantTable.setStatus(TableStatus.AVAILABLE);
            restaurantTables.add(restaurantTable);
        }
        return restaurantTables;
    }
}
