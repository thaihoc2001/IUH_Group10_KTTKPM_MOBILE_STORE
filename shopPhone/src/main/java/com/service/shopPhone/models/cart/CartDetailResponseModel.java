package com.service.shopPhone.models.cart;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CartDetailResponseModel {
    private UUID id;
    private int quantity;
    private String productName;
    private Double productPrice;
    private String productImage; 
    private UUID productId;
}
