package com.service.shopPhone.service.brand;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.brand.AddBrandRequestModel;
import com.service.shopPhone.models.brand.AddBrandResponseModel;
import com.service.shopPhone.models.brand.GetAllBrandRequestModel;
import com.service.shopPhone.models.brand.GetAllBrandResponseModel;

import java.util.UUID;

public interface IBrandService {

    public Response<GetAllBrandResponseModel> getAll(GetAllBrandRequestModel model);

    public Response<AddBrandResponseModel> addBrand(AddBrandRequestModel model);

    public Response<StatusResponseModel> deleteBrand(AddBrandRequestModel requestModel, UUID brandId);
}
