package com.service.shopPhone.models.product;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDetailProductResponseModel {
    private UUID id;
    private String name;
    private String type;
    private Double price;
    private String status;
    private String description;
    private String screen;
    private String operatingSystem;
    private String chip;
    private String ram;
    private String internalMemory;
    private String rearCamera;
    private String frontCamera;
    private String brandName;
    private int quantity;
}
