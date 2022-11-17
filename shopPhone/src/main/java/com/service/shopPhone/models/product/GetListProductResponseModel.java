package com.service.shopPhone.models.product;

import java.util.List;

import com.service.shopPhone.models.BaseListEntityResponseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetListProductResponseModel extends BaseListEntityResponseModel{
    private List<ProductResponseModel> items;
}
