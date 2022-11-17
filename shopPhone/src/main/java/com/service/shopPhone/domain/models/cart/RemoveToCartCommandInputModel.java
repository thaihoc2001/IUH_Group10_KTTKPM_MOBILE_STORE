package com.service.shopPhone.domain.models.cart;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveToCartCommandInputModel {
    private UUID cartDetailId;
}
