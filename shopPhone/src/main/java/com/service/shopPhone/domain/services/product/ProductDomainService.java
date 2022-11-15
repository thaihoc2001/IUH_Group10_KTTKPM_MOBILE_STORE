package com.service.shopPhone.domain.services.product;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.product.AddProductCommand;
import com.service.shopPhone.domain.commands.product.UpdateProductCommand;
import com.service.shopPhone.domain.models.product.AddProductCommandInputModel;
import com.service.shopPhone.domain.models.product.AddProductCommandResultModel;
import com.service.shopPhone.domain.models.product.UpdateProductCommandInputModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductDomainService {
    private final AddProductCommand addProductCommand;
    private final UpdateProductCommand updateProductCommand;

    @Autowired
    public ProductDomainService(
        AddProductCommand addProductCommand,
        UpdateProductCommand updateProductCommand) {
        this.addProductCommand = addProductCommand;
        this.updateProductCommand = updateProductCommand;
    }

    public UUID addProduct(AddProductCommandInputModel model) {
        AddProductCommandResultModel result = addProductCommand.execute(model);
        return result.getProductId();
    }

    public boolean updateProduct(UpdateProductCommandInputModel inputModel) {
        return updateProductCommand.execute(inputModel).isSuccess();
    }
}
