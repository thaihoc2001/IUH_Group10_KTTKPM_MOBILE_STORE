package com.service.shopPhone.models.brand;

import java.util.List;

import com.service.shopPhone.models.BaseListEntityResponseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllBrandResponseModel extends BaseListEntityResponseModel{
    private List<BrandResponseModel> items;
}
