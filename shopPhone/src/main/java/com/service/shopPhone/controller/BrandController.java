package com.service.shopPhone.controller;

import javax.validation.Valid;

import com.service.shopPhone.models.StatusResponseModel;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.brand.AddBrandRequestModel;
import com.service.shopPhone.models.brand.AddBrandResponseModel;
import com.service.shopPhone.models.brand.GetAllBrandRequestModel;
import com.service.shopPhone.models.brand.GetAllBrandResponseModel;
import com.service.shopPhone.service.brand.IBrandService;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/brands")
public class BrandController {
    
    private final IBrandService brandService;

    @Autowired
    public BrandController(IBrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public Response<GetAllBrandResponseModel> getAll(
        @RequestParam(required = false) String searchText,
        @ParameterObject Pageable pageable
    ) {
        GetAllBrandRequestModel requestModel = new GetAllBrandRequestModel(searchText, pageable);
        return brandService.getAll(requestModel);
    }

    @PostMapping("")
    public Response<AddBrandResponseModel> addBrand(@Valid @RequestBody AddBrandRequestModel requestModel) {
        return brandService.addBrand(requestModel);
    }

    @PutMapping("/{brandId}")
    public Response<StatusResponseModel> deleteBrand(@RequestBody AddBrandRequestModel requestModel, @PathVariable String brandId){
        return brandService.deleteBrand(requestModel,UUID.fromString(brandId));
    }
}
