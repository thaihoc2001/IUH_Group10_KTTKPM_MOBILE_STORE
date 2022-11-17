package com.service.shopPhone.domain.quries.product;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.exceptions.ProductNotExistsException;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.ProductReponsitory;

@Service
public class ProductQuery {
    private final ProductReponsitory productReponsitory;

    @Autowired
    ProductQuery(ProductReponsitory productReponsitory) {
        this.productReponsitory = productReponsitory;
    }

    public Page<ProductEntity> getAll(String searchText, Pageable pageable) {
        return productReponsitory.searchProduct(searchText, pageable);
    }

    public ProductEntity getProductById(UUID productId) {
        Optional<ProductEntity> prOptional = productReponsitory.findById(productId);
        if (!prOptional.isPresent()) {
            throw new ProductNotExistsException();
        }
        return prOptional.get();
    }
}
