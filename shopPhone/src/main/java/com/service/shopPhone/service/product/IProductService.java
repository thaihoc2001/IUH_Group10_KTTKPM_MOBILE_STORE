package com.service.shopPhone.service.product;

import java.util.UUID;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.product.AddProductRequestModel;
import com.service.shopPhone.models.product.AddProductResponseModel;

public interface IProductService {

    Response<AddProductResponseModel> addProduct(AddProductRequestModel requestModel);
    Response<StatusResponseModel> updateProduct(AddProductRequestModel requestModel, UUID productId);
}
