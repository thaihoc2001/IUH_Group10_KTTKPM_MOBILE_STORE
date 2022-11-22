package com.service.shopPhone.models.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAddressRequestModel {
    private String state;

    private String city;

    private String detail;

    private String code;
}
