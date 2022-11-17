package com.service.shopPhone.domain.services.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.image.AddImageCommand;
import com.service.shopPhone.domain.models.brand.AddBrandCommandInputModel;
import com.service.shopPhone.domain.models.image.AddImageCommandInputModel;

@Service
public class ImageDomainService {
    private final AddImageCommand addImageCommand;

    @Autowired
    public ImageDomainService(AddImageCommand addImageCommand) {
        this.addImageCommand = addImageCommand;
    }

    public boolean createImage(AddImageCommandInputModel inputModel) {
        return addImageCommand.execute(inputModel).isSuccess();
    }
}
