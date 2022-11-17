package com.service.shopPhone.domain.models.product;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeteleProductCommandInputModel {
    private List<UUID> arrayProductId;
}
