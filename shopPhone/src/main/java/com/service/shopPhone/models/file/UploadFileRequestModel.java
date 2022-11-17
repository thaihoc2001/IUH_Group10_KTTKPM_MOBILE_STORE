package com.service.shopPhone.models.file;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileRequestModel {
    private MultipartFile multipartFile;
    private String type;
    private UUID productId;
}
