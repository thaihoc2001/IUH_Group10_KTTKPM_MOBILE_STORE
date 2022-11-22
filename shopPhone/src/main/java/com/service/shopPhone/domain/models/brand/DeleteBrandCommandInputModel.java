package com.service.shopPhone.domain.models.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBrandCommandInputModel {
    private UUID brandId;
    private String name;
}
