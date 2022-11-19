package com.service.shopPhone.service.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.service.shopPhone.domain.models.brand.DeleteBrandCommandInputModel;
import com.service.shopPhone.models.StatusResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.models.brand.GetListBrandQueryModel;
import com.service.shopPhone.domain.services.brand.BrandDomainService;
import com.service.shopPhone.entity.BrandEntity;
import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.brand.AddBrandRequestModel;
import com.service.shopPhone.models.brand.AddBrandResponseModel;
import com.service.shopPhone.models.brand.BrandResponseModel;
import com.service.shopPhone.models.brand.GetAllBrandRequestModel;
import com.service.shopPhone.models.brand.GetAllBrandResponseModel;
import com.service.shopPhone.utility.RequestCorrelation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BrandService implements IBrandService{

    private final ModelMapper modelMapper;
    private final BrandDomainService brandDomainService;

    @Autowired
    public BrandService(BrandDomainService brandDomainService, ModelMapper modelMapper) {
        this.brandDomainService = brandDomainService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<GetAllBrandResponseModel> getAll(GetAllBrandRequestModel model) {
        GetListBrandQueryModel queryModel = modelMapper.map(model, GetListBrandQueryModel.class);
        Page<BrandEntity> result = brandDomainService.getAll(queryModel);

        List<BrandEntity> brands = result.getContent();
        List<BrandResponseModel> items = new ArrayList<>();
        for (BrandEntity brand : brands) {
            BrandResponseModel item = modelMapper.map(brand, BrandResponseModel.class);
            items.add(item);
        }
        GetAllBrandResponseModel data = new GetAllBrandResponseModel();
        data.setCurrentPage(result.getPageable().getPageNumber());
        data.setPageSize(result.getPageable().getPageSize());
        data.setItems(items);
        data.setTotalItems(result.getTotalElements());
        data.setTotalPages(result.getTotalPages());
        return Response.<GetAllBrandResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(data)
            .build();
    }

    @Override
    public Response<AddBrandResponseModel> addBrand(AddBrandRequestModel model) {
        UUID result = brandDomainService.addBrand(model.getName());
        return Response.<AddBrandResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(AddBrandResponseModel.builder().brandId(result).build())
            .build();
    }

    @Override
    public Response<StatusResponseModel> deleteBrand(AddBrandRequestModel requestModel, UUID brandId){
        DeleteBrandCommandInputModel inputModel = modelMapper.map(requestModel, DeleteBrandCommandInputModel.class);
        inputModel.setBrandId(brandId);
        boolean success = brandDomainService.deleteBrand(inputModel);
        return Response.<StatusResponseModel>builder()
                .id(RequestCorrelation.getRequestId())
                .data(StatusResponseModel.builder().success(success).build())
                .build();
    }
}
