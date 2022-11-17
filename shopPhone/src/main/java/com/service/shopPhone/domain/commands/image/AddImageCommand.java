package com.service.shopPhone.domain.commands.image;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.image.AddImageCommandInputModel;
import com.service.shopPhone.entity.ImageEntity;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.ImageRepository;

@Service
public class AddImageCommand implements ICommand<AddImageCommandInputModel,StatusCommandResultModel>{

    private final ImageRepository imageRepository;

    public AddImageCommand(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public StatusCommandResultModel execute(AddImageCommandInputModel input) {
        ImageEntity entity = new ImageEntity();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(input.getProductId());
        entity.setId(UUID.randomUUID());
        entity.setProduct(productEntity);
        entity.setType(input.getType());
        entity.setUrl(input.getUrl());
        imageRepository.save(entity);

        return StatusCommandResultModel.builder().success(true).build();
    }
    
}
