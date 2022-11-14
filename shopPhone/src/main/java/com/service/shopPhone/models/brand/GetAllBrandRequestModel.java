package com.service.shopPhone.models.brand;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandRequestModel {
    private String searchText;
    private Pageable pageable;
}
