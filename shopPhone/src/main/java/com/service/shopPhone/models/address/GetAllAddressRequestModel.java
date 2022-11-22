package com.service.shopPhone.models.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAddressRequestModel {
    private String searchText;
    private Pageable pageable;
}
