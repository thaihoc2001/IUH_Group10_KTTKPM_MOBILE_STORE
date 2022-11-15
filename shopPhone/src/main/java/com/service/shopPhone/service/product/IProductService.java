package com.service.shopPhone.service.product;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.product.AddProductRequestModel;
import com.service.shopPhone.models.product.AddProductResponseModel;

public interface IProductService {

    Response<AddProductResponseModel> addProduct(AddProductRequestModel requestModel);
}
