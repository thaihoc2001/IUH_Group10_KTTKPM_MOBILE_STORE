package com.service.shopPhone.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.models.product.AddProductCommandInputModel;
import com.service.shopPhone.domain.models.product.UpdateProductCommandInputModel;
import com.service.shopPhone.domain.services.product.ProductDomainService;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.product.AddProductRequestModel;
import com.service.shopPhone.models.product.AddProductResponseModel;
import com.service.shopPhone.models.product.DeleteProductRequestModel;
import com.service.shopPhone.models.product.GetAllProductRequestModel;
import com.service.shopPhone.models.product.GetDetailProductResponseModel;
import com.service.shopPhone.models.product.GetListProductResponseModel;
import com.service.shopPhone.models.product.ProductResponseModel;
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

    @Override
    public Response<GetListProductResponseModel> getAll(GetAllProductRequestModel requestModel) {
        Page<ProductEntity> page = productDomainService.getAll(requestModel.getSearchText(), requestModel.getPageable());
        List<ProductResponseModel> items = new ArrayList<>();

        for (ProductEntity product : page.getContent()) {
            ProductResponseModel item = modelMapper.map(product, ProductResponseModel.class);
            item.setBrandName(product.getBrand().getName());
            items.add(item);
        }

        GetListProductResponseModel res = new GetListProductResponseModel();
        res.setItems(items);
        res.setCurrentPage(page.getPageable().getPageNumber());
        res.setPageSize(page.getPageable().getPageSize());
        res.setTotalItems(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        return Response.<GetListProductResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(res)
            .build();
    }

    @Override
    public Response<StatusResponseModel> deleteProduct(DeleteProductRequestModel requestModel) {
        boolean result = productDomainService.deleteProduct(requestModel.getArrayProductId());
        return Response.<StatusResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(StatusResponseModel.builder().success(result).build())
            .build();
    }

    @Override
    public Response<GetDetailProductResponseModel> getDetailProduct(UUID productId) {
        ProductEntity result = productDomainService.getById(productId);
        GetDetailProductResponseModel res = modelMapper.map(result, GetDetailProductResponseModel.class);
        return Response.<GetDetailProductResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(res)
            .build();
    }
    
}
