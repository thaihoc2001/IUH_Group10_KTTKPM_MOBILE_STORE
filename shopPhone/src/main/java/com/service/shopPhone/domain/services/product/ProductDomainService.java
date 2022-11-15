package com.service.shopPhone.domain.services.product;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.product.AddProductCommand;
import com.service.shopPhone.domain.models.product.AddProductCommandInputModel;
import com.service.shopPhone.domain.models.product.AddProductCommandResultModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductDomainService {
    private final AddProductCommand addProductCommand;

    @Autowired
    public ProductDomainService(AddProductCommand addProductCommand) {
        this.addProductCommand = addProductCommand;
    }

    public UUID addProduct(AddProductCommandInputModel model) {
        AddProductCommandResultModel result = addProductCommand.execute(model);
        return result.getProductId();
    }
}
