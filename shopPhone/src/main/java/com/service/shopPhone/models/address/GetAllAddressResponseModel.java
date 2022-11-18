package com.service.shopPhone.models.address;


import com.service.shopPhone.models.BaseListEntityResponseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class GetAllAddressResponseModel extends BaseListEntityResponseModel {
    private List<AddressResponseModel> items;
}
