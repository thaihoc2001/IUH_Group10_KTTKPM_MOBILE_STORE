package com.service.shopPhone.models.cart;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CartResponseModel {
    private UUID id;
    private int countProduct;
    private Double totalPrice;
    private List<CartDetailResponseModel> cartDetails;
}
