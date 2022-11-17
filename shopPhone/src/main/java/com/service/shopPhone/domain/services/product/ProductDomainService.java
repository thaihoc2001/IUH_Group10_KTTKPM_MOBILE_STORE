package com.service.shopPhone.domain.services.product;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.product.AddProductCommand;
import com.service.shopPhone.domain.commands.product.DeleteProductCommand;
import com.service.shopPhone.domain.commands.product.UpdateProductCommand;
import com.service.shopPhone.domain.models.product.AddProductCommandInputModel;
import com.service.shopPhone.domain.models.product.AddProductCommandResultModel;
import com.service.shopPhone.domain.models.product.DeteleProductCommandInputModel;
import com.service.shopPhone.domain.models.product.UpdateProductCommandInputModel;
import com.service.shopPhone.domain.quries.product.ProductQuery;
import com.service.shopPhone.entity.ProductEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductDomainService {
    private final AddProductCommand addProductCommand;
    private final UpdateProductCommand updateProductCommand;
    private final ProductQuery productQuery;
    private final DeleteProductCommand deleteProductCommand;

    @Autowired
    public ProductDomainService(
        AddProductCommand addProductCommand,
        UpdateProductCommand updateProductCommand,
        ProductQuery productQuery,
        DeleteProductCommand deleteProductCommand) {
        this.addProductCommand = addProductCommand;
        this.updateProductCommand = updateProductCommand;
        this.productQuery = productQuery;
        this.deleteProductCommand = deleteProductCommand;
    }

    public UUID addProduct(AddProductCommandInputModel model) {
        AddProductCommandResultModel result = addProductCommand.execute(model);
        return result.getProductId();
    }

    public boolean updateProduct(UpdateProductCommandInputModel inputModel) {
        return updateProductCommand.execute(inputModel).isSuccess();
    }

    public Page<ProductEntity> getAll(String searchText, Pageable pageable) {
        return productQuery.getAll(searchText, pageable);
    }

    public boolean deleteProduct(List<UUID> arrayProductId) {
        return deleteProductCommand.execute(
            DeteleProductCommandInputModel.builder().arrayProductId(arrayProductId).build()
        ).isSuccess();
    }

    public ProductEntity getById(UUID productId) {
        return productQuery.getProductById(productId);
    }
}
