package com.service.shopPhone.domain.commands.product;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.exceptions.ProductNotExistsException;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.product.DeteleProductCommandInputModel;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.ProductReponsitory;

@Service
public class DeleteProductCommand implements ICommand<DeteleProductCommandInputModel, StatusCommandResultModel>{

    private final ProductReponsitory productReponsitory;

    public DeleteProductCommand(ProductReponsitory productReponsitory){
        this.productReponsitory = productReponsitory;
    }
    @Override
    public StatusCommandResultModel execute(DeteleProductCommandInputModel input) {
        
        for(UUID productId: input.getArrayProductId()) {
            Optional<ProductEntity> productOptional = productReponsitory.findById(productId);
            if (!productOptional.isPresent()) {
                throw new ProductNotExistsException();
            }
            ProductEntity entity = productOptional.get();
            entity.setIsDeleted(true);
            productReponsitory.save(entity);
        }
        return StatusCommandResultModel.builder().success(true).build();
    }
    
}
