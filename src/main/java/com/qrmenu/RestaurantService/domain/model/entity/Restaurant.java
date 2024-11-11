package com.qrmenu.RestaurantService.domain.model.entity;

import com.qrmenu.RestaurantService.domain.mapper.UUIDAttributeConverter;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @Convert(converter = UUIDAttributeConverter.class)
    private Set<UUID> managerIds;

    @ElementCollection
    @Convert(converter = UUIDAttributeConverter.class)
    private Set<UUID> waiterIds;

    @ElementCollection
    @Convert(converter = UUIDAttributeConverter.class)
    private Set<UUID> cookIds;
    private String name;
    private String address;
    private Integer tableCount;

    private String accountInfo; // You may want to handle this more securely.

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
