package com.service.shopPhone.domain.quries.brand;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.service.shopPhone.entity.BrandEntity;
import com.service.shopPhone.repository.BrandReponsitory;

@Service
public class BrandQuery {
    private final BrandReponsitory brandReponsitory;

    public BrandQuery(BrandReponsitory brandReponsitory) {
        this.brandReponsitory = brandReponsitory;
    }

    public Page<BrandEntity> getAll(String searchText, Pageable pageable) {
        return brandReponsitory.searchBrand(searchText, pageable);
    }

    public Optional<BrandEntity> findByName(String name){
        return brandReponsitory.findByName(name);
    };
}
