package com.service.shopPhone.domain.models.brand;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListBrandQueryModel {
    private String searchText;
    private Pageable pageable;
}
