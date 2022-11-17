package com.service.shopPhone.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.file.UploadFileRequestModel;
import com.service.shopPhone.service.image.IImageService;
import com.service.shopPhone.utility.FirebaseUpload;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class FileController {

    // @Autowired
    // private FirebaseUpload firebaseUpload;

    private final IImageService imageService;

    @Autowired
    public FileController(IImageService imageService){
        // this.firebaseUpload = firebaseUpload;
        this.imageService = imageService;
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response<StatusResponseModel> upload(@RequestParam("productId") UUID productId, @RequestParam("file") MultipartFile multipartFile, @RequestParam("type") String type) {
        // String url = firebaseUpload.upload(multipartFile);
        // System.out.println("======================>" + url);
        UploadFileRequestModel requestModel = new UploadFileRequestModel(multipartFile, type, productId);
        return imageService.createImage(requestModel);
    }
}
