package com.service.shopPhone.domain.services.image;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.image.AddImageCommand;
import com.service.shopPhone.domain.models.image.AddImageCommandInputModel;
import com.service.shopPhone.domain.quries.image.ImageQuery;
import com.service.shopPhone.entity.ImageEntity;

@Service
public class ImageDomainService {
    private final AddImageCommand addImageCommand;
    private final ImageQuery imageQuery;

    @Autowired
    public ImageDomainService(AddImageCommand addImageCommand, ImageQuery imageQuery) {
        this.addImageCommand = addImageCommand;
        this.imageQuery = imageQuery;
    }

    public boolean createImage(AddImageCommandInputModel inputModel) {
        return addImageCommand.execute(inputModel).isSuccess();
    }

    public List<ImageEntity> getByProduct(UUID productId) {
        return imageQuery.getAllByProduct(productId);
    }

    public ImageEntity getMainByProduct(UUID productId) {
        return imageQuery.getImageMainByProduct(productId);
    }
}
