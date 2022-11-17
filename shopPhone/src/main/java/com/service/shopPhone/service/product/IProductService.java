package com.service.shopPhone.service.product;

import java.util.UUID;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.product.AddProductRequestModel;
import com.service.shopPhone.models.product.AddProductResponseModel;
import com.service.shopPhone.models.product.DeleteProductRequestModel;
import com.service.shopPhone.models.product.GetAllProductRequestModel;
import com.service.shopPhone.models.product.GetDetailProductResponseModel;
import com.service.shopPhone.models.product.GetListProductResponseModel;

public interface IProductService {

    Response<AddProductResponseModel> addProduct(AddProductRequestModel requestModel);
    Response<StatusResponseModel> updateProduct(AddProductRequestModel requestModel, UUID productId);

    Response<GetListProductResponseModel> getAll(GetAllProductRequestModel requestModel);
    Response<StatusResponseModel> deleteProduct(DeleteProductRequestModel requestModel);
    Response<GetDetailProductResponseModel> getDetailProduct(UUID productId);

}
