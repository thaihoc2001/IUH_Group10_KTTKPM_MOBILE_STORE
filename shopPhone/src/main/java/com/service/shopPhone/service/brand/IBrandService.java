package com.service.shopPhone.service.brand;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.brand.AddBrandRequestModel;
import com.service.shopPhone.models.brand.AddBrandResponseModel;
import com.service.shopPhone.models.brand.GetAllBrandRequestModel;
import com.service.shopPhone.models.brand.GetAllBrandResponseModel;

public interface IBrandService {

    public Response<GetAllBrandResponseModel> getAll(GetAllBrandRequestModel model);

    public Response<AddBrandResponseModel> addBrand(AddBrandRequestModel model);
}
