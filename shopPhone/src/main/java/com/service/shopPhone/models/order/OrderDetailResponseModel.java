package com.service.shopPhone.models.order;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.service.shopPhone.models.cart.CartDetailResponseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderDetailResponseModel {
    private UUID id;
    private String status;
    private String note;
    private Double totalPrice;
    private String username;
    private OffsetDateTime createdAt;
    private List<CartDetailResponseModel> details;
}
