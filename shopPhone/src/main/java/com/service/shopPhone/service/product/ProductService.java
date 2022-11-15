package com.service.shopPhone.service.product;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.models.product.AddProductCommandInputModel;
import com.service.shopPhone.domain.models.product.UpdateProductCommandInputModel;
import com.service.shopPhone.domain.services.product.ProductDomainService;
import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.product.AddProductRequestModel;
import com.service.shopPhone.models.product.AddProductResponseModel;
import com.service.shopPhone.utility.RequestCorrelation;

@Service
public class ProductService implements IProductService{

    private final ProductDomainService productDomainService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductDomainService productDomainService, ModelMapper modelMapper) {
        this.productDomainService = productDomainService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<AddProductResponseModel> addProduct(AddProductRequestModel requestModel) {
        AddProductCommandInputModel inputModel = modelMapper.map(requestModel, AddProductCommandInputModel.class);
        UUID result =  productDomainService.addProduct(inputModel);
        return Response.<AddProductResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(AddProductResponseModel.builder().productId(result).build())
            .build();
    }

    @Override
    public Response<StatusResponseModel> updateProduct(AddProductRequestModel requestModel, UUID productId) {
        UpdateProductCommandInputModel inputModel = modelMapper.map(requestModel, UpdateProductCommandInputModel.class);
        inputModel.setProductId(productId);
        boolean success = productDomainService.updateProduct(inputModel);
        return Response.<StatusResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(StatusResponseModel.builder().success(success).build())
            .build();
    }
    
}
