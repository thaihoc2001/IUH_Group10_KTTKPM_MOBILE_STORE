package com.service.shopPhone.service.image;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.models.image.AddImageCommandInputModel;
import com.service.shopPhone.domain.services.image.ImageDomainService;
import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.file.UploadFileRequestModel;
import com.service.shopPhone.utility.FirebaseUpload;
import com.service.shopPhone.utility.RequestCorrelation;

@Service
public class ImageService implements IImageService{
    
    private final ImageDomainService imageDomainService;
    private final ModelMapper modelMapper;
    private final FirebaseUpload firebaseUpload;


    @Autowired
    public ImageService(ImageDomainService imageDomainService, ModelMapper modelMapper, FirebaseUpload firebaseUpload){
        this.imageDomainService = imageDomainService;
        this.modelMapper = modelMapper;
        this.firebaseUpload = firebaseUpload;
    }

    @Override
    public Response<StatusResponseModel> createImage(UploadFileRequestModel requestModel) {
        AddImageCommandInputModel inputModel = modelMapper.map(requestModel, AddImageCommandInputModel.class);
        String url = firebaseUpload.upload(requestModel.getMultipartFile());
        inputModel.setUrl(url);
        boolean result = imageDomainService.createImage(inputModel); 
       
        return Response.<StatusResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(StatusResponseModel.builder().success(result).build())
            .build();
    }
}
