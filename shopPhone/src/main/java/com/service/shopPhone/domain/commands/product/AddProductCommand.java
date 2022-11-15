package com.service.shopPhone.domain.commands.product;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.product.AddProductCommandInputModel;
import com.service.shopPhone.domain.models.product.AddProductCommandResultModel;
import com.service.shopPhone.entity.BrandEntity;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.ProductReponsitory;

@Service
public class AddProductCommand implements ICommand<AddProductCommandInputModel, AddProductCommandResultModel>{

    private final ProductReponsitory productReponsitory;
    private final ModelMapper modelMapper;

    public AddProductCommand(ProductReponsitory productReponsitory, ModelMapper modelMapper) {
        this.productReponsitory = productReponsitory;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddProductCommandResultModel execute(AddProductCommandInputModel input) {
        
        ProductEntity productEntity = modelMapper.map(input, ProductEntity.class);
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(UUID.fromString(input.getBrandId()));
        productEntity.setBrand(brandEntity);
        productEntity.setId(UUID.randomUUID());
        productEntity.setIsDeleted(false);

        productEntity = productReponsitory.save(productEntity);
        return AddProductCommandResultModel.builder().productId(productEntity.getId()).build();
    }
    
}
