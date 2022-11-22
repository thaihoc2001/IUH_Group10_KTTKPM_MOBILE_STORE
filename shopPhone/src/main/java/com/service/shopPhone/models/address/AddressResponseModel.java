package com.service.shopPhone.models.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseModel {
    private UUID id;
    private String state;
    private String city;
    private String detail;
    private String code;
}
