package com.service.shopPhone.domain.models.order;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderCommandInputModel {
    private String username;
    private Double coupon;
    private String note;
    private String typePayment;
}
