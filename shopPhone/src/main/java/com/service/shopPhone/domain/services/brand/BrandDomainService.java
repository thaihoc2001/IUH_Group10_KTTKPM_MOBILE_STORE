package com.service.shopPhone.domain.services.brand;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.brand.AddBrandCommand;
import com.service.shopPhone.domain.exceptions.BrandNameExistedException;
import com.service.shopPhone.domain.models.brand.AddBrandCommandInputModel;
import com.service.shopPhone.domain.models.brand.AddBrandCommandResultModel;
import com.service.shopPhone.domain.models.brand.GetListBrandQueryModel;
import com.service.shopPhone.domain.quries.brand.BrandQuery;
import com.service.shopPhone.entity.BrandEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BrandDomainService {
    private final BrandQuery brandQuery;
    private final AddBrandCommand addBrandCommand;

    @Autowired
    public BrandDomainService(BrandQuery brandQuery, AddBrandCommand addBrandCommand) {
        this.brandQuery = brandQuery;
        this.addBrandCommand = addBrandCommand;
    }

    public Page<BrandEntity> getAll(GetListBrandQueryModel model) {
        return brandQuery.getAll(model.getSearchText(), model.getPageable());
    }

    public UUID addBrand(String name) {
        Optional<BrandEntity> brand = brandQuery.findByName(name);
        if (brand.isPresent()) {
            throw new BrandNameExistedException();
        }
        AddBrandCommandResultModel result = addBrandCommand.execute(
            AddBrandCommandInputModel.builder().name(name).build()
        );
        return result.getBrandId();
    }

}
