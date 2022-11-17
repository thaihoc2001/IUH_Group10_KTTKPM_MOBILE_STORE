package com.service.shopPhone.domain.models.image;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddImageCommandInputModel {
    private String url;
    private String type;
    private UUID productId;
}
