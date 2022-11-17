package com.service.shopPhone.service.image;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.file.UploadFileRequestModel;

public interface IImageService {
    public Response<StatusResponseModel> createImage(UploadFileRequestModel requestModel);
}
