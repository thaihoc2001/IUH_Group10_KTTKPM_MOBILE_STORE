package com.service.shopPhone.domain.commands.brand;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.brand.AddBrandCommandInputModel;
import com.service.shopPhone.domain.models.brand.AddBrandCommandResultModel;
import com.service.shopPhone.entity.BrandEntity;
import com.service.shopPhone.repository.BrandReponsitory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddBrandCommand implements ICommand<AddBrandCommandInputModel, AddBrandCommandResultModel>{

    private final BrandReponsitory brandReponsitory;

    @Autowired
    public AddBrandCommand(BrandReponsitory brandReponsitory) {
        this.brandReponsitory = brandReponsitory;
    }

    @Override
    public AddBrandCommandResultModel execute(AddBrandCommandInputModel input) {
        try {
            BrandEntity entity = new BrandEntity();
            entity.setId(UUID.randomUUID());
            entity.setName(input.getName());
            entity.setIsDeleted(false);
            entity = brandReponsitory.save(entity);
            return AddBrandCommandResultModel.builder().brandId(entity.getId()).build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
