package com.service.shopPhone.models.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ImageResponseModel {
    private String type;
    private String url;
}
