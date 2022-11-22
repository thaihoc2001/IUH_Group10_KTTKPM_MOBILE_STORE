package com.service.shopPhone.domain.models.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListAddressQueryModel {
    private String searchText;
    private Pageable pageable;
}
