package com.service.shopPhone.domain.models.address;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressCommandInputModel {
    private String city;
    private String state;
    private String detail;
    private String code;
}
