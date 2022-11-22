package com.service.shopPhone.domain.commands.brand;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.exceptions.BrandNotExistsException;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.brand.DeleteBrandCommandInputModel;
import com.service.shopPhone.entity.BrandEntity;
import com.service.shopPhone.repository.BrandReponsitory;


import java.util.Optional;


public class DeleteBrandCommand implements ICommand<DeleteBrandCommandInputModel, StatusCommandResultModel> {
    private final BrandReponsitory brandReponsitory;

    public DeleteBrandCommand(BrandReponsitory brandReponsitory) {
        this.brandReponsitory = brandReponsitory;
    }

    @Override
    public StatusCommandResultModel execute(DeleteBrandCommandInputModel input){
        Optional<BrandEntity> brandOptional = brandReponsitory.findByName(input.getName());

        if(!brandOptional.isPresent()){
            throw new BrandNotExistsException();
        }

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandOptional.get().getId());
        brandEntity.setIsDeleted(true);

        brandEntity = brandReponsitory.save(brandEntity);
        return StatusCommandResultModel.builder().success(true).build();
    }
}
