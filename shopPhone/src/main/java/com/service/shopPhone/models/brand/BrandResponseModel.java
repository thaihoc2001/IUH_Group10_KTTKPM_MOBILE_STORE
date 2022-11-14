package com.service.shopPhone.models.brand;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponseModel {
    private UUID id;
    private String name;
}
