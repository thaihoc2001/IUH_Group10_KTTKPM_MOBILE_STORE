package com.service.shopPhone.service.address;


import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.address.AddAddressRequestModel;
import com.service.shopPhone.models.address.AddAddressResponseModel;
import com.service.shopPhone.models.address.GetAllAddressRequestModel;
import com.service.shopPhone.models.address.GetAllAddressResponseModel;

public interface IAddressService {
    public Response<GetAllAddressResponseModel> getAll(GetAllAddressRequestModel model);

    public Response<AddAddressResponseModel> addAddress(AddAddressRequestModel model);
}
