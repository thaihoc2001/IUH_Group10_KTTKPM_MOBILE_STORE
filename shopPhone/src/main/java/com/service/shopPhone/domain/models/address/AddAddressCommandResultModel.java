package com.service.shopPhone.domain.models.address;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressCommandResultModel {
    private UUID idAddress;
}
