package com.service.shopPhone.domain.commands.product;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.exceptions.ProductNotExistsException;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.product.AddProductCommandInputModel;
import com.service.shopPhone.domain.models.product.AddProductCommandResultModel;
import com.service.shopPhone.domain.models.product.UpdateProductCommandInputModel;
import com.service.shopPhone.entity.BrandEntity;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.ProductReponsitory;

@Service
public class UpdateProductCommand implements ICommand<UpdateProductCommandInputModel, StatusCommandResultModel>{

    private final ProductReponsitory productReponsitory;
    private final ModelMapper modelMapper;

    public UpdateProductCommand(ProductReponsitory productReponsitory, ModelMapper modelMapper) {
        this.productReponsitory = productReponsitory;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatusCommandResultModel execute(UpdateProductCommandInputModel input) {
        Optional<ProductEntity> productOptional = productReponsitory.findById(input.getProductId());

        if (!productOptional.isPresent()) {
            throw new ProductNotExistsException();
        }

        ProductEntity productEntity = modelMapper.map(input, ProductEntity.class);
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(UUID.fromString(input.getBrandId()));
        productEntity.setBrand(brandEntity);
        productEntity.setId(productOptional.get().getId());
        productEntity.setIsDeleted(false);

        productEntity = productReponsitory.save(productEntity);
        return StatusCommandResultModel.builder().success(true).build();
    }
    
    
}
