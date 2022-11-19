package com.service.shopPhone.models.order;

import java.util.List;

import com.service.shopPhone.models.BaseListEntityResponseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllOrderResponseModel extends BaseListEntityResponseModel{
    private List<OrderResponseModel> items;
}
