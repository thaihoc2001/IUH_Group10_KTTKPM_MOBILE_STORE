package com.service.shopPhone.models.product;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductRequestModel {
    private String searchText;
    private Pageable pageable;
}
