package com.service.shopPhone.domain.models.address;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAddressByUserIdResultModel {
    private String username;
    private String city;
}
